package pl.msiwak.multiplatform.ui.addExercise

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.common.DateFilter
import pl.msiwak.multiplatform.data.common.DateFilterType
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.FormattedResultData
import pl.msiwak.multiplatform.data.common.ResultData
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
    private val formatStringToDateUseCase: FormatStringToDateUseCase,
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
            val exerciseWithUnit = getExerciseUseCase(exerciseId)
            currentExerciseData.value = exerciseWithUnit.exerciseData
            currentResults.addAll(currentExerciseData.value.results)
            val results = formatResultsUseCase(currentExerciseData.value.results)
            exerciseName = currentExerciseData.value.exerciseTitle
            _viewState.value = _viewState.value.copy(
                exerciseTitle = currentExerciseData.value.exerciseTitle,
                results = results,
                resultDataTitles = setTableTitles(),
                unit = exerciseWithUnit.unit
            )
        }
    }

    private fun setTableTitles(): List<String> {
        return when (currentExerciseData.value.exerciseType) {
            ExerciseType.RUNNING -> listOf("Distance", "Time", "Date")
            ExerciseType.GYM -> listOf("Weight", "Reps", "Date")
//            ExerciseType.OTHER -> emptyList()
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

    fun onSaveResultClicked() {
        if (_viewState.value.newResultData.result.isEmpty()) {
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(1))
            return
        }
        if (_viewState.value.newResultData.amount.isEmpty()) {
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(2))
            return
        }
        if (_viewState.value.newResultData.date.isEmpty()) {
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(3))
            return
        }
        viewModelScope.launch {
            currentResults.add(0, prepareResultData())
            saveResult()

            _viewState.value = _viewState.value.copy(
                results = formatResultsUseCase(currentResults),
                isResultFieldEnabled = false,
                newResultData = FormattedResultData()
            )
        }
    }

    private fun prepareResultData(): ResultData {
        val newResultData = _viewState.value.newResultData
        val date = formatStringToDateUseCase(newResultData.date)
        return ResultData(newResultData.result.toDouble(), newResultData.amount.toDouble(), date)
    }

    private suspend fun saveResult() {
        val newExercise = currentExerciseData.value.copy(
            results = currentResults
        )
        updateExerciseUseCase(newExercise)
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
                filterResults(dateFilter)
                dateFilter.copy(isSelected = true)
            } else {
                dateFilter.copy(isSelected = false)
            }
        }
        _viewState.value =
            _viewState.value.copy(filter = filterList, selectedFilterPosition = pos)
    }

    private fun filterResults(dateFilter: DateFilter) {
        when (dateFilter.type) {
            DateFilterType.ALL -> filterAll()
            DateFilterType.DAY -> filterDay()
            DateFilterType.WEEK -> filter(7)
            DateFilterType.MONTH -> filter(31)
            DateFilterType.YEAR -> filter(365)
        }
    }

    private fun filterAll() {
        val newResults = formatResultsUseCase(currentResults)
        _viewState.value = _viewState.value.copy(results = newResults)
    }

    private fun filterDay() {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val newResults = formatResultsUseCase(currentResults.filter {
            it.date.dayOfYear == currentDate.dayOfYear
        })
        _viewState.value = _viewState.value.copy(results = newResults)
    }

    private fun filter(previousDaysCount: Int) {
        val currentDate = Clock.System.now()
        val newResults = formatResultsUseCase(currentResults.filter {
            val diff =
                currentDate.minus(it.date.toInstant(TimeZone.currentSystemDefault())).inWholeDays
            diff in 0..previousDaysCount
        })
        _viewState.value = _viewState.value.copy(results = newResults)
    }
}