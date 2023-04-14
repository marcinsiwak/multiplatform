package pl.msiwak.multiplatform.ui.addExercise

import kotlin.math.roundToInt
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.FormattedResultData
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.common.UnitType
import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.domain.summaries.FormatDateUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatResultsUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatStringToDateUseCase
import pl.msiwak.multiplatform.domain.summaries.GetExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateExerciseUseCase
import pl.msiwak.multiplatform.ui.navigator.Navigator

class AddExerciseViewModel(
    id: Long,
    private val updateExerciseUseCase: UpdateExerciseUseCase,
    private val navigator: Navigator,
    private val formatDateUseCase: FormatDateUseCase,
    private val formatResultsUseCase: FormatResultsUseCase,
    private val getExerciseUseCase: GetExerciseUseCase,
    private val formatStringToDateUseCase: FormatStringToDateUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddExerciseState())
    val viewState: StateFlow<AddExerciseState> = _viewState

    private val _viewEvent = MutableSharedFlow<AddExerciseEvent>(extraBufferCapacity = 1)
    val viewEvent: SharedFlow<AddExerciseEvent> = _viewEvent

    private var pickedDate: LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    private val currentResults: MutableList<ResultData> = mutableListOf()

    private val currentExerciseData = MutableStateFlow(ExerciseData())

    private val exerciseId = id

    private var exerciseToRemovePosition: Int? = null

    private var exerciseName: String? = null

    init {
        viewModelScope.launch {
            currentExerciseData.value = getExerciseUseCase(exerciseId)
            currentResults.addAll(currentExerciseData.value.results)
            val results = formatResultsUseCase(currentExerciseData.value.results)
            exerciseName = currentExerciseData.value.exerciseTitle
            _viewState.value = _viewState.value.copy(
                exerciseTitle = currentExerciseData.value.exerciseTitle,
                results = results,
                resultDataTitles = setTableTitles()
            )
        }
    }

    private fun setTableTitles(): List<String> {
        return when (currentExerciseData.value.exerciseType) {
            ExerciseType.RUNNING -> listOf("Distance", "Time", "Date")
            ExerciseType.GYM -> listOf("Weight", "Reps", "Date")
            ExerciseType.OTHER -> emptyList()
        }
    }

    fun onPause() {
        viewModelScope.launch {
            val newTitle = viewState.value.exerciseTitle
            if (exerciseName != newTitle) {
                updateExerciseUseCase(currentExerciseData.value.copy(exerciseTitle = newTitle))
            }
        }
    }

    fun onExerciseTitleChanged(title: String) {
        _viewState.value = _viewState.value.copy(exerciseTitle = title)
    }

    fun onAddNewResultClicked() {
        _viewState.value = _viewState.value.copy(isResultFieldEnabled = true)
    }

    fun onAddNewExerciseClicked() {
        viewModelScope.launch {
            val newResultData = _viewState.value.newResultData
            val date = formatStringToDateUseCase(newResultData.date)
            val resultData =
                ResultData(newResultData.result.toDouble(), newResultData.amount.toDouble(), date)

            currentResults.add(resultData)
            val newExercise = currentExerciseData.value.copy(
                results = currentResults
            )

            updateExerciseUseCase(newExercise)
            _viewState.value = _viewState.value.copy(
                results = formatResultsUseCase(currentResults),
                isResultFieldEnabled = false,
                newResultData = FormattedResultData()
            )
        }
    }

    fun onDateClicked() {
        _viewEvent.tryEmit(AddExerciseEvent.OpenCalendar)
    }

    fun onDatePicked(date: LocalDateTime) {
        val formattedDate = formatDateUseCase(date)
        pickedDate = date
        _viewState.value =
            _viewState.value.copy(newResultData = _viewState.value.newResultData.copy(date = formattedDate))
    }

    fun onResultLongClicked(resultIndex: Int) {
        exerciseToRemovePosition = resultIndex
        _viewState.value = _viewState.value.copy(isRemoveExerciseDialogVisible = true)
    }

    fun onResultRemoved() {
        viewModelScope.launch {
            exerciseToRemovePosition?.let {
                currentResults.removeAt(it)
                val results = formatResultsUseCase(currentResults)
                val newExercise = currentExerciseData.value.copy(
                    results = currentResults
                )
                updateExerciseUseCase(newExercise)
                _viewState.value = _viewState.value.copy(results = results)
            }
            _viewState.value = _viewState.value.copy(isRemoveExerciseDialogVisible = false)
        }
    }

    fun onPopupDismissed() {
        _viewState.value = _viewState.value.copy(isRemoveExerciseDialogVisible = false)
    }


    fun onResultValueChanged(text: String) {
        _viewState.value =
            _viewState.value.copy(newResultData = _viewState.value.newResultData.copy(result = text))
    }

    fun onAmountValueChanged(text: String) {
        _viewState.value =
            _viewState.value.copy(newResultData = _viewState.value.newResultData.copy(amount = text))

    }

    fun onDateValueChanged(text: String) {
        _viewState.value =
            _viewState.value.copy(newResultData = _viewState.value.newResultData.copy(date = text))

    }

    fun onTabClicked(pos: Int) {
        val filterList = _viewState.value.filter.mapIndexed { index, dateFilter ->
            if (index == pos) {
                dateFilter.copy(isSelected = true)
            } else {
                dateFilter.copy(isSelected = false)
            }
        }
        _viewState.value =
            _viewState.value.copy(filter = filterList, selectedFilterPosition = pos)
    }

    fun onChangeUnitClicked() {
        val currentUnitType = _viewState.value.unitType
        val exerciseType = currentExerciseData.value.exerciseType

        if (currentUnitType == UnitType.IMPERIAL) {
            val results = _viewState.value.results.map {
                it.copy(result = it.result.toDouble().div(exerciseType.convertValue).toString())
            }
            _viewState.value = _viewState.value.copy(
                unitType = UnitType.METRIC,
                unit = exerciseType.unitMetric,
            )

        } else {
            val results = _viewState.value.results.map {
                it.copy(
                    result = it.result.toDouble().times(exerciseType.convertValue).roundToInt()
                        .toString()
                )
            }
            _viewState.value = _viewState.value.copy(
                unitType = UnitType.IMPERIAL,
                unit = exerciseType.unitImperial,
            )
        }
    }
}