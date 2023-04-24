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
import pl.msiwak.multiplatform.data.common.SortType
import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.domain.summaries.FormatDateUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatResultsUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatStringToDateUseCase
import pl.msiwak.multiplatform.domain.summaries.GetExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateExerciseUseCase
import pl.msiwak.multiplatform.extensions.isNumber
import pl.msiwak.multiplatform.extensions.isTime
import pl.msiwak.multiplatform.extensions.safeToDouble
import pl.msiwak.multiplatform.utils.DATE_REGEX
import pl.msiwak.multiplatform.utils.NUMBER_REGEX_COMMA
import pl.msiwak.multiplatform.utils.NUMBER_REGEX_DOT
import pl.msiwak.multiplatform.utils.TIME_REGEX

class AddExerciseViewModel(
    id: Long,
    private val updateExerciseUseCase: UpdateExerciseUseCase,
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
                exerciseType = currentExerciseData.value.exerciseType,
                results = results,
                resultDataTitles = setTableTitles(exerciseWithUnit.exerciseData.exerciseType),
                unit = exerciseWithUnit.unit
            )
        }
    }

    private fun setTableTitles(exerciseType: ExerciseType): List<String> {
        return when (exerciseType) {
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
        val exerciseType = _viewState.value.exerciseType
        val savedResult = _viewState.value.newResultData.result
        val savedAmount = _viewState.value.newResultData.amount
        val savedDate = _viewState.value.newResultData.date

        if (savedResult.isEmpty()) {
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(1))
            return
        }
        if (savedAmount.isEmpty()) {
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(2))
            return
        }
        if (savedDate.isEmpty()) {
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(3))
            return
        }
        if (!savedDate.matches(Regex(DATE_REGEX))) {
            _viewState.value = _viewState.value.copy(
                newResultData = _viewState.value.newResultData.copy(isDateError = true)
            )
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(3))
            return
        }


        if (!(savedResult.matches(Regex(NUMBER_REGEX_DOT)) || savedResult.matches(
                Regex(
                    NUMBER_REGEX_COMMA
                )
            ) && exerciseType == ExerciseType.GYM)
        ) {
            _viewState.value = _viewState.value.copy(
                newResultData = _viewState.value.newResultData.copy(isResultError = true)
            )
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(1))
            return
        }

        if (!(savedAmount.matches(Regex(NUMBER_REGEX_DOT)) || savedAmount.matches(
                Regex(
                    NUMBER_REGEX_DOT
                )
            )) && exerciseType == ExerciseType.GYM
        ) {
            _viewState.value = _viewState.value.copy(
                newResultData = _viewState.value.newResultData.copy(isAmountError = true)
            )
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(2))
            return
        }

        if (!(savedAmount.matches(Regex(TIME_REGEX))) && exerciseType == ExerciseType.RUNNING
        ) {
            _viewState.value = _viewState.value.copy(
                newResultData = _viewState.value.newResultData.copy(isAmountError = true)
            )
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(2))
            return
        }

        viewModelScope.launch {
            val data = ResultData(
                savedResult.safeToDouble(),
                savedAmount,
                formatStringToDateUseCase(savedDate)
            )
            currentResults.add(0, data)
            saveResult()

            _viewState.value = _viewState.value.copy(
                results = formatResultsUseCase(currentResults),
                isResultFieldEnabled = false,
                newResultData = FormattedResultData()
            )
        }
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

    fun onLabelClicked(labelPosition: Int) {
        val currentSortType = _viewState.value.sortType
        val sortType = when {
            labelPosition == 0 && currentSortType != SortType.RESULT_DECREASING -> SortType.RESULT_DECREASING
            labelPosition == 0 && currentSortType != SortType.RESULT_INCREASING -> SortType.RESULT_INCREASING
            labelPosition == 1 && currentSortType != SortType.AMOUNT_DECREASING -> SortType.AMOUNT_DECREASING
            labelPosition == 1 && currentSortType != SortType.AMOUNT_INCREASING -> SortType.AMOUNT_INCREASING
            labelPosition == 2 && currentSortType != SortType.DATE_DECREASING -> SortType.DATE_DECREASING
            labelPosition == 2 && currentSortType != SortType.DATE_INCREASING -> SortType.DATE_INCREASING
            else -> SortType.DATE_DECREASING
        }
        _viewState.value = _viewState.value.copy(sortType = sortType)
        when (sortType) {
            SortType.RESULT_INCREASING -> currentResults.sortBy { it.result }
            SortType.AMOUNT_INCREASING -> currentResults.sortBy { it.amount }
            SortType.DATE_INCREASING -> currentResults.sortBy { it.date }
            SortType.RESULT_DECREASING -> currentResults.sortByDescending { it.result }
            SortType.AMOUNT_DECREASING -> currentResults.sortByDescending { it.amount }
            SortType.DATE_DECREASING -> currentResults.sortByDescending { it.date }
        }
        _viewState.value = _viewState.value.copy(results = formatResultsUseCase(currentResults))
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
            _viewState.value.copy(
                newResultData = _viewState.value.newResultData.copy(
                    result = text.filter { it.isNumber() },
                    isResultError = false
                )
            )
    }

    fun onAmountValueChanged(text: String) {
        val amount = text.filter {
            if (_viewState.value.exerciseType == ExerciseType.RUNNING) {
                it.isTime()
            } else {
                it.isNumber()
            }
        }
        _viewState.value =
            _viewState.value.copy(
                newResultData = _viewState.value.newResultData.copy(
                    amount = amount,
                    isAmountError = false
                )
            )
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