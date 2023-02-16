package pl.msiwak.multiplatform.ui.addExercise

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.domain.summaries.InsertSummaryUseCase
import pl.msiwak.multiplatform.ui.navigator.Navigator

class AddExerciseViewModel(
    private val insertSummaryUseCase: InsertSummaryUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddExerciseState())
    val viewState: StateFlow<AddExerciseState> = _viewState

    private val _viewEvent = MutableSharedFlow<AddExerciseEvent>(extraBufferCapacity = 1)
    val viewEvent: SharedFlow<AddExerciseEvent> = _viewEvent

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
        _viewState.value = _viewState.value.copy(results = currentResults)
    }

    fun onAddNewExerciseClicked() {
        val type = _viewState.value.exerciseTitle
        val results = _viewState.value.results
        insertSummaryUseCase(Summary(exerciseTitle = type, results = results))
        navigator.navigateUp()
    }

    fun onDateClicked() {
        _viewEvent.tryEmit(AddExerciseEvent.OpenCalendar)
    }

    fun onDatePicked(date: LocalDateTime) {
        _viewState.value = _viewState.value.copy(newResultDate = date)
    }

    fun onResultRemoved(resultIndex: Int) {
        val currentResults = _viewState.value.results.toMutableList()
        currentResults.removeAt(resultIndex)
        _viewState.value = _viewState.value.copy(results = currentResults)
    }
}