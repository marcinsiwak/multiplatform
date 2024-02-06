package pl.msiwak.multiplatform.ui.addExercise

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import pl.msiwak.multiplatform.commonObject.DateFilterType
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.FormattedResultData
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.commonObject.ResultTableItemData
import pl.msiwak.multiplatform.commonObject.SortType
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.domain.settings.GetUnitsUseCase
import pl.msiwak.multiplatform.domain.summaries.AddResultUseCase
import pl.msiwak.multiplatform.domain.summaries.DownloadExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatDateUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatResultsUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatRunningAmountToMillisecondsUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatRunningAmountUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveResultUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateExerciseNameUseCase
import pl.msiwak.multiplatform.utils.DATE_REGEX
import pl.msiwak.multiplatform.utils.NUMBER_REGEX_COMMA
import pl.msiwak.multiplatform.utils.NUMBER_REGEX_DOT
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.utils.extensions.isNumber
import pl.msiwak.multiplatform.utils.extensions.isTime

class AddExerciseViewModel(
    private val id: String,
    private val formatDateUseCase: FormatDateUseCase,
    private val formatResultsUseCase: FormatResultsUseCase,
    private val addResultUseCase: AddResultUseCase,
    private val removeResultUseCase: RemoveResultUseCase,
    private val downloadExerciseUseCase: DownloadExerciseUseCase,
    private val observeExerciseUseCase: ObserveExerciseUseCase,
    private val updateExerciseNameUseCase: UpdateExerciseNameUseCase,
    private val getUnitsUseCase: GetUnitsUseCase,
    private val formatRunningAmountUseCase: FormatRunningAmountUseCase,
    private val formatRunningAmountToMillisecondsUseCase: FormatRunningAmountToMillisecondsUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddExerciseState())
    val viewState: StateFlow<AddExerciseState> = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<AddExerciseEvent>(extraBufferCapacity = 1)
    val viewEvent: SharedFlow<AddExerciseEvent> = _viewEvent.asSharedFlow()

    private var pickedDate: LocalDateTime =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    private val currentResults: MutableList<ResultData> = mutableListOf()

    private var exerciseToRemovePosition: Int? = null

    private var currentExercise: Exercise? = null
    private var currentExerciseType: ExerciseType = ExerciseType.GYM

    private var sortType: SortType = SortType.DATE_DECREASING

    private val errorHandler = globalErrorHandler.handleError()

    init {
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            downloadExerciseUseCase(id)
            _viewState.update { it.copy(isLoading = false) }
        }
        viewModelScope.launch(errorHandler) {
            observeExerciseUseCase(id).collect { exercise ->
                currentResults.clear()
                currentResults.addAll(exercise.results)
                currentExercise = exercise
                currentExerciseType = exercise.exerciseType
                _viewState.update {
                    it.copy(
                        exerciseTitle = exercise.exerciseTitle,
                        results = formatResultsUseCase(
                            FormatResultsUseCase.Params(
                                exercise.results,
                                exercise.exerciseType
                            )
                        ),
                        resultDataTitles = setTableTitles(exercise.exerciseType),
                        exerciseType = exercise.exerciseType,
                        newResultData = it.newResultData.copy(date = formatDateUseCase(pickedDate))
                    )
                }
            }
        }
    }

    private fun setTableTitles(exerciseType: ExerciseType): List<ResultTableItemData> {
        val unit = getUnitsUseCase()
        return when (exerciseType) {
            ExerciseType.RUNNING -> listOf(
                ResultTableItemData("Distance [${exerciseType.getUnit(unit)}]"),
                ResultTableItemData("Time"),
                ResultTableItemData("Date")
            )

            ExerciseType.GYM -> listOf(
                ResultTableItemData("Weight [${exerciseType.getUnit(unit)}]"),
                ResultTableItemData("Reps"),
                ResultTableItemData("Date")
            )
        }
    }

    fun onPause() {
        viewModelScope.launch(errorHandler) {
            val newTitle = viewState.value.exerciseTitle
            if (currentExercise != null && currentExercise?.exerciseTitle != newTitle) {
                updateExerciseNameUseCase(currentExercise!!.copy(exerciseTitle = newTitle))
            }
        }
    }

    fun onTitleClicked() {
        _viewState.update { it.copy(isEditNameEnabled = true) }
    }

    fun onExerciseTitleChanged(title: String) {
        _viewState.update { it.copy(exerciseTitle = title) }
    }

    fun onAddNewResultClicked() {
        _viewState.update { it.copy(isResultFieldEnabled = true) }
        pickedDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
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
            _viewState.update { it.copy(newResultData = it.newResultData.copy(isDateError = true)) }
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(3))
            return
        }

        if (!isInputGymCorrect(savedResult, exerciseType)) {
            _viewState.update { it.copy(newResultData = it.newResultData.copy(isResultError = true)) }
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(1))
            return
        }

        if (!isInputGymCorrect(savedResult, exerciseType)) {
            _viewState.update { it.copy(newResultData = it.newResultData.copy(isAmountError = true)) }
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(2))
            return
        }

        if (savedAmount.isEmpty() && exerciseType == ExerciseType.RUNNING) {
            _viewState.update { it.copy(newResultData = it.newResultData.copy(isAmountError = true)) }
            _viewEvent.tryEmit(AddExerciseEvent.FocusOnInput(2))
            return
        }

        viewModelScope.launch(errorHandler) {
            val data = ResultData(
                exerciseId = id,
                result = savedResult,
                amount = savedAmount,
                date = pickedDate
            )
            addResultUseCase(AddResultUseCase.Params(data, currentExerciseType))

            _viewState.update {
                it.copy(
                    results = formatResultsUseCase(
                        FormatResultsUseCase.Params(
                            currentResults,
                            currentExerciseType
                        )
                    ),
                    isResultFieldEnabled = false,
                    newResultData = FormattedResultData(date = _viewState.value.newResultData.date)
                )
            }
        }
    }

    private fun isCorrectRegex(input: String, regex: Regex): Boolean {
        return input.matches(regex)
    }

    private fun isInputGymCorrect(savedResult: String, exerciseType: ExerciseType): Boolean {
        return isCorrectRegex(savedResult, Regex(NUMBER_REGEX_DOT)) || isCorrectRegex(
            savedResult,
            Regex(NUMBER_REGEX_COMMA)
        ) && exerciseType == ExerciseType.GYM
    }

    fun onDateClicked() {
        _viewEvent.tryEmit(AddExerciseEvent.OpenCalendar)
    }

    fun onAmountClicked() {
        _viewState.update { it.copy(isTimeInputDialogVisible = true) }
    }

    fun onDismissAmountDialog() {
        _viewState.update { it.copy(isTimeInputDialogVisible = false) }
    }

    fun onConfirmRunningAmount(
        hours: String,
        minutes: String,
        seconds: String,
        milliseconds: String
    ) {
        _viewState.update {
            it.copy(
                isTimeInputDialogVisible = false,
                newResultData = it.newResultData.copy(
                    amount = formatRunningAmountUseCase(
                        FormatRunningAmountUseCase.Params(
                            hours,
                            minutes,
                            seconds,
                            milliseconds
                        )
                    )
                )
            )
        }
    }

    fun onDatePicked(date: LocalDateTime) {
        val formattedDate = formatDateUseCase(date)
        pickedDate = date
        _viewState.update { it.copy(newResultData = it.newResultData.copy(date = formattedDate)) }
    }

    fun onResultLongClicked(resultIndex: Int) {
        exerciseToRemovePosition = resultIndex
        _viewState.update { it.copy(isRemoveExerciseDialogVisible = true) }
    }

    fun onLabelClicked(labelPosition: Int) {
        sortResults(labelPosition)

        _viewState.update {
            it.copy(
                resultDataTitles = setTitlesArrow(labelPosition),
                results = formatResultsUseCase(
                    FormatResultsUseCase.Params(
                        currentResults,
                        currentExerciseType
                    )
                )
            )
        }
    }

    fun onResultRemoved() {
        viewModelScope.launch {
            exerciseToRemovePosition?.let {
                val id = currentResults[it].id
                removeResultUseCase(id)
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
                if (text.length >= 8) {
                    return
                }
                it.isTime()
            } else {
                it.isNumber()
            }
        }
        _viewState.update {
            it.copy(newResultData = it.newResultData.copy(amount = amount, isAmountError = false))
        }
    }

    fun onDateValueChanged(text: String) {
        _viewState.update { it.copy(newResultData = _viewState.value.newResultData.copy(date = text)) }
    }

    fun onTabClicked(item: DateFilterType) {
        val pos = _viewState.value.filter.indexOf(item)
        viewState.value.filter.forEach { dateFilter ->
            if (dateFilter == item) {
                filterResults(dateFilter)
            }
        }
        _viewState.update { it.copy(selectedFilterPosition = pos) }
    }

    private fun filterResults(dateFilter: DateFilterType) {
        when (dateFilter) {
            DateFilterType.ALL -> filterAll()
            DateFilterType.DAY -> filterDay()
            DateFilterType.WEEK -> filter(7)
            DateFilterType.MONTH -> filter(31)
            DateFilterType.YEAR -> filter(365)
        }
    }

    private fun filterAll() {
        val newResults = formatResultsUseCase(
            FormatResultsUseCase.Params(
                currentResults,
                currentExerciseType
            )
        )
        _viewState.update { it.copy(results = newResults) }
    }

    private fun filterDay() {
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val filteredResults = currentResults.filter {
            it.date.dayOfYear == currentDate.dayOfYear
        }
        val newResults =
            formatResultsUseCase(FormatResultsUseCase.Params(filteredResults, currentExerciseType))
        _viewState.value = _viewState.value.copy(results = newResults)
    }

    private fun filter(previousDaysCount: Int) {
        val currentDate = Clock.System.now()
        val filteredResults = currentResults.filter {
            val diff =
                currentDate.minus(it.date.toInstant(TimeZone.currentSystemDefault())).inWholeDays
            diff in 0..previousDaysCount
        }
        val newResults =
            formatResultsUseCase(FormatResultsUseCase.Params(filteredResults, currentExerciseType))
        _viewState.value = _viewState.value.copy(results = newResults)
    }

    private fun sortResults(labelPosition: Int) {
        sortType = when {
            labelPosition == 0 && sortType != SortType.RESULT_DECREASING -> SortType.RESULT_DECREASING
            labelPosition == 0 && sortType != SortType.RESULT_INCREASING -> SortType.RESULT_INCREASING
            labelPosition == 1 && sortType != SortType.AMOUNT_DECREASING -> SortType.AMOUNT_DECREASING
            labelPosition == 1 && sortType != SortType.AMOUNT_INCREASING -> SortType.AMOUNT_INCREASING
            labelPosition == 2 && sortType != SortType.DATE_DECREASING -> SortType.DATE_DECREASING
            labelPosition == 2 && sortType != SortType.DATE_INCREASING -> SortType.DATE_INCREASING
            else -> SortType.DATE_DECREASING
        }

        when (sortType) {
            SortType.RESULT_INCREASING -> currentResults.sortBy { it.result.toDouble() }
            SortType.AMOUNT_INCREASING -> {
                if (currentExerciseType == ExerciseType.RUNNING) {
                    currentResults.sortBy { formatRunningAmountToMillisecondsUseCase(it.amount) }
                } else {
                    currentResults.sortBy { it.amount.toDouble() }
                }
            }

            SortType.DATE_INCREASING -> currentResults.sortBy { it.date }
            SortType.RESULT_DECREASING -> currentResults.sortByDescending { it.result.toDouble() }
            SortType.AMOUNT_DECREASING -> {
                if (currentExerciseType == ExerciseType.RUNNING) {
                    currentResults.sortByDescending { formatRunningAmountToMillisecondsUseCase(it.amount) }
                } else {
                    currentResults.sortByDescending { it.amount.toDouble() }
                }
            }

            SortType.DATE_DECREASING -> currentResults.sortByDescending { it.date }
        }
    }

    private fun setTitlesArrow(labelPosition: Int): List<ResultTableItemData> {
        return viewState.value.resultDataTitles.mapIndexed { index, item ->
            if (index == labelPosition) {
                item.copy(isArrowUp = item.isArrowUp?.not() ?: true)
            } else {
                item.copy(isArrowUp = null)
            }
        }
    }
}