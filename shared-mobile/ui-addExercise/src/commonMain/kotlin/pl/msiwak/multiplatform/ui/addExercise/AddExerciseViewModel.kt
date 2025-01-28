package pl.msiwak.multiplatform.ui.addExercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
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
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler
import pl.msiwak.multiplatform.utils.extensions.isNumber
import pl.msiwak.multiplatform.utils.extensions.isTime
import pl.msiwak.multiplatform.utils.validators.ResultValidator

class AddExerciseViewModel(
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
    globalErrorHandler: GlobalErrorHandler,
    private val resultValidator: ResultValidator
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddExerciseState())
    val viewState: StateFlow<AddExerciseState> = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<AddExerciseEvent>(extraBufferCapacity = 1)
    val viewEvent: SharedFlow<AddExerciseEvent> = _viewEvent.asSharedFlow()

    private var pickedDate: Long = Clock.System.now().toEpochMilliseconds()

    private var currentResults: MutableList<ResultData> = mutableListOf()

    private var exerciseToRemovePosition: Int? = null

    private var currentExercise: Exercise? = null

    private var sortType: SortType = SortType.DATE_DECREASING

    private val errorHandler = globalErrorHandler.handleError {
        _viewState.update { it.copy(isLoading = false) }
        false
    }

    private lateinit var exerciseId: String

    fun onInit(id: String) {
        exerciseId = id
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            downloadExerciseUseCase(id)
            _viewState.update { it.copy(isLoading = false) }
        }
        viewModelScope.launch(errorHandler) {
            observeExerciseUseCase(id).collect { exercise ->
                currentExercise = exercise
                currentResults = exercise.results.toMutableList()
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
                        newResultData = FormattedResultData(date = formatDateUseCase(pickedDate))
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

    fun onUiAction(action: AddExerciseUiAction) {
        when (action) {
            AddExerciseUiAction.OnAddNewResultClicked -> onAddNewResultClicked()
            AddExerciseUiAction.OnAmountClicked -> onAmountClicked()
            is AddExerciseUiAction.OnAmountValueChanged -> onAmountValueChanged(action.amount)
            is AddExerciseUiAction.OnConfirmRunningAmount -> onConfirmRunningAmount(
                action.hours,
                action.minutes,
                action.seconds,
                action.milliseconds
            )

            AddExerciseUiAction.OnDateClicked -> onDateClicked()
            is AddExerciseUiAction.OnDateConfirmClicked -> onDatePicked(action.selectedDateMillis)
            AddExerciseUiAction.OnDateDismiss -> onDateDismiss()
            is AddExerciseUiAction.OnDateValueChanged -> onDateValueChanged(action.date)
            AddExerciseUiAction.OnDismissAmountDialog -> onDismissAmountDialog()
            is AddExerciseUiAction.OnLabelClicked -> onLabelClicked(action.index)
            AddExerciseUiAction.OnPopupDismissed -> onPopupDismissed()
            is AddExerciseUiAction.OnResultLongClicked -> onResultLongClicked(action.pos)
            AddExerciseUiAction.OnResultRemoved -> onResultRemoved()
            is AddExerciseUiAction.OnResultValueChanged -> onResultValueChanged(action.result)
            AddExerciseUiAction.OnSaveResultClicked -> onSaveResultClicked()
            is AddExerciseUiAction.OnTabClicked -> onTabClicked(action.dateFilterType)
        }
    }

    private fun onAddNewResultClicked() {
        _viewState.update { it.copy(isResultFieldEnabled = true) }
        pickedDate = Clock.System.now().toEpochMilliseconds()
    }

    private fun onSaveResultClicked() {
        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch(errorHandler) {
            with(viewState.value.newResultData) {
                when (resultValidator.validate(result, amount, date, viewState.value.exerciseType)) {
                    ResultValidator.ResultValidationResponse.IncorrectResult -> {
                        _viewState.update { it.copy(newResultData = it.newResultData.copy(isResultError = true)) }
                        _viewEvent.emit(AddExerciseEvent.FocusOnInput(1))
                        return@launch
                    }

                    ResultValidator.ResultValidationResponse.IncorrectAmount -> {
                        _viewState.update { it.copy(newResultData = it.newResultData.copy(isAmountError = true)) }
                        _viewEvent.emit(AddExerciseEvent.FocusOnInput(2))
                        return@launch
                    }

                    ResultValidator.ResultValidationResponse.IncorrectDate -> {
                        _viewEvent.emit(AddExerciseEvent.FocusOnInput(3))
                        return@launch
                    }

                    null -> Unit
                }

                addResultUseCase(
                    AddResultUseCase.Params(
                        exerciseId = exerciseId,
                        result = result,
                        amount = amount,
                        date = Instant.fromEpochMilliseconds(pickedDate)
                            .toLocalDateTime(TimeZone.currentSystemDefault()),
                        exerciseType = currentExercise!!.exerciseType
                    )
                )

                _viewState.update { it.copy(isResultFieldEnabled = false, isLoading = false) }
            }
        }
    }

    private fun onDateClicked() {
        _viewState.update { it.copy(isDatePickerVisible = true) }
    }

    private fun onAmountClicked() {
        _viewState.update { it.copy(isTimeInputDialogVisible = true) }
    }

    private fun onDismissAmountDialog() {
        _viewState.update { it.copy(isTimeInputDialogVisible = false) }
    }

    private fun onConfirmRunningAmount(
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

    private fun onDatePicked(date: Long?) {
        if (date == null) return
        pickedDate = date
        _viewState.update {
            it.copy(
                newResultData = it.newResultData.copy(date = formatDateUseCase(date)),
                isDatePickerVisible = false
            )
        }
    }

    private fun onResultLongClicked(resultIndex: Int) {
        exerciseToRemovePosition = resultIndex
        _viewState.update { it.copy(isRemoveExerciseDialogVisible = true) }
    }

    private fun onLabelClicked(labelPosition: Int) {
        sortResults(labelPosition)

        _viewState.update {
            it.copy(
                resultDataTitles = setTitlesArrow(labelPosition),
                results = formatResultsUseCase(
                    FormatResultsUseCase.Params(
                        currentResults,
                        currentExercise!!.exerciseType
                    )
                )
            )
        }
    }

    private fun onResultRemoved() {
        viewModelScope.launch {
            exerciseToRemovePosition?.let {
                val id = currentResults[it].id
                removeResultUseCase(id)
            }
            _viewState.update { it.copy(isRemoveExerciseDialogVisible = false) }
        }
    }

    private fun onPopupDismissed() {
        _viewState.update { it.copy(isRemoveExerciseDialogVisible = false) }
    }

    private fun onResultValueChanged(text: String) {
        _viewState.update {
            it.copy(
                newResultData = _viewState.value.newResultData.copy(
                    result = text.filter { it.isNumber() },
                    isResultError = false
                )
            )
        }
    }

    private fun onAmountValueChanged(text: String) {
        val amount = text.filter {
            if (viewState.value.exerciseType == ExerciseType.RUNNING) {
                if (text.length >= 8) return
                it.isTime()
            } else {
                it.isNumber()
            }
        }
        _viewState.update { it.copy(newResultData = it.newResultData.copy(amount = amount, isAmountError = false)) }
    }

    private fun onDateValueChanged(text: String) {
        _viewState.update { it.copy(newResultData = _viewState.value.newResultData.copy(date = text)) }
    }

    private fun onTabClicked(item: DateFilterType) {
        viewState.value.filter.forEach { dateFilter ->
            if (dateFilter == item) {
                filterResults(dateFilter)
            }
        }
        _viewState.update { it.copy(selectedFilterPosition = viewState.value.filter.indexOf(item)) }
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
        _viewState.update {
            it.copy(
                results = formatResultsUseCase(
                    FormatResultsUseCase.Params(
                        currentResults,
                        currentExercise!!.exerciseType
                    )
                )
            )
        }
    }

    private fun filterDay() {
        val filteredResults = currentResults.filter {
            it.date.dayOfYear == Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).dayOfYear
        }
        _viewState.update {
            it.copy(
                results = formatResultsUseCase(
                    FormatResultsUseCase.Params(
                        filteredResults,
                        currentExercise!!.exerciseType
                    )
                )
            )
        }
    }

    private fun filter(previousDaysCount: Int) {
        val filteredResults = currentResults.filter {
            Clock.System.now()
                .minus(it.date.toInstant(TimeZone.currentSystemDefault())).inWholeDays in 0..previousDaysCount
        }
        _viewState.update {
            it.copy(
                results = formatResultsUseCase(
                    FormatResultsUseCase.Params(
                        filteredResults,
                        currentExercise!!.exerciseType
                    )
                )
            )
        }
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
                if (currentExercise!!.exerciseType == ExerciseType.RUNNING) {
                    currentResults.sortBy { formatRunningAmountToMillisecondsUseCase(it.amount) }
                } else {
                    currentResults.sortBy { it.amount.toDouble() }
                }
            }

            SortType.DATE_INCREASING -> currentResults.sortBy { it.date }
            SortType.RESULT_DECREASING -> currentResults.sortByDescending { it.result.toDouble() }
            SortType.AMOUNT_DECREASING -> {
                if (currentExercise!!.exerciseType == ExerciseType.RUNNING) {
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

    private fun onDateDismiss() {
        _viewState.update { it.copy(isDatePickerVisible = false) }
    }
}
