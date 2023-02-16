package pl.msiwak.multiplatform.ui.exercise

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.domain.summaries.GetSummaryUseCase
import pl.msiwak.multiplatform.domain.summaries.UpdateSummaryUseCase

class ExerciseViewModel(
    private val getSummaryUseCase: GetSummaryUseCase,
    private val updateSummaryUseCase: UpdateSummaryUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(ExerciseState())
    val viewState: StateFlow<ExerciseState> = _viewState

    private val _viewEvent = MutableSharedFlow<ExerciseEvent>(extraBufferCapacity = 1)
    val viewEvent: SharedFlow<ExerciseEvent> = _viewEvent

    private lateinit var currentSummary: Summary

    fun onInit(id: Long) {
        currentSummary = getSummaryUseCase(id)
        _viewState.value = _viewState.value.copy(
            exerciseTitle = currentSummary.exerciseType,
            results = currentSummary.results,
        )
    }

    fun onExerciseTitleChanged(title: String) {
        _viewState.value = _viewState.value.copy(exerciseTitle = title)
    }

    fun onExerciseNewResultChanged(title: String) {
        _viewState.value = _viewState.value.copy(newResult = title)
    }

    fun onAddNewResultClicked() {
        val currentResults = _viewState.value.results.toMutableList()
        val newResult = _viewState.value.newResult
        val newResultDate = _viewState.value.newResultDate
        currentResults.add(ResultData(newResult, newResultDate))

        val newSummary = currentSummary.copy(results = currentResults)
        updateSummaryUseCase(newSummary)
        val summary = getSummaryUseCase(newSummary.id)

        _viewState.value = _viewState.value.copy(results = summary.results)
    }

    fun onDateClicked() {
        _viewEvent.tryEmit(ExerciseEvent.OpenCalendar)
    }

    fun onDatePicked(date: LocalDateTime) {
        _viewState.value = _viewState.value.copy(newResultDate = date)
    }

    fun onResultRemoved(resultIndex: Int) {
        val currentResults = _viewState.value.results.toMutableList()
        currentResults.removeAt(resultIndex)
        val newSummary = currentSummary.copy(results = currentResults)
        updateSummaryUseCase(newSummary)
        val summary = getSummaryUseCase(newSummary.id)
        _viewState.value = _viewState.value.copy(results = currentResults)
    }
}