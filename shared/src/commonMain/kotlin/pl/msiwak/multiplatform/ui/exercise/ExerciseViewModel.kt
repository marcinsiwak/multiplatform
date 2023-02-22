package pl.msiwak.multiplatform.ui.exercise

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.common.FormattedResultData
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.domain.summaries.FormatDateUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatResultsUseCase
import pl.msiwak.multiplatform.domain.summaries.GetSummaryUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateSummaryUseCase

class ExerciseViewModel(
    id: Long,
    private val getSummaryUseCase: GetSummaryUseCase,
    private val updateSummaryUseCase: UpdateSummaryUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val formatResultsUseCase: FormatResultsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(ExerciseState())
    val viewState: StateFlow<ExerciseState> = _viewState

    private val _viewEvent = MutableSharedFlow<ExerciseEvent>(extraBufferCapacity = 1)
    val viewEvent: SharedFlow<ExerciseEvent> = _viewEvent

    private var currentSummary: Summary = getSummaryUseCase(id)

    private var pickedDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    private var currentResults: MutableList<ResultData> = mutableListOf()

    init {
        currentResults.addAll(currentSummary.results)
        val results = formatResultsUseCase(currentSummary.results)
        _viewState.value = _viewState.value.copy(
            exerciseTitle = currentSummary.exerciseTitle,
            results = results,
        )
    }

    fun onExerciseTitleChanged(title: String) {
        _viewState.value = _viewState.value.copy(exerciseTitle = title)
    }

    fun onExerciseNewResultChanged(title: String) {
        _viewState.value = _viewState.value.copy(newResult = title)
    }

    fun onAddNewResultClicked() {
        val newResult = _viewState.value.newResult
        currentResults.add(ResultData(newResult, pickedDate))

        val newSummary = currentSummary.copy(results = currentResults.toList())
        updateSummaryUseCase(newSummary)
        val summary = getSummaryUseCase(newSummary.id)

        val results = formatResultsUseCase(summary.results)
        _viewState.value = _viewState.value.copy(results = results)
    }

    fun onDateClicked() {
        _viewEvent.tryEmit(ExerciseEvent.OpenCalendar)
    }

    fun onDatePicked(date: LocalDateTime) {
        val formattedDate = formatDateUseCase(date)
        pickedDate = date
        _viewState.value = _viewState.value.copy(newResultDate = formattedDate)
    }

    fun onResultRemoved(resultIndex: Int) {
        currentResults.removeAt(resultIndex)
        val newSummary = currentSummary.copy(results = currentResults)
        updateSummaryUseCase(newSummary)
        val summary = getSummaryUseCase(newSummary.id)

        val results = formatResultsUseCase(summary.results)
        _viewState.value = _viewState.value.copy(results = results)
    }
}