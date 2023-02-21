package pl.msiwak.multiplatform.ui.addExercise

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.entity.SummaryEntity
import pl.msiwak.multiplatform.domain.summaries.InsertSummaryUseCase
import pl.msiwak.multiplatform.ui.navigator.Navigator
import pl.msiwak.multiplatform.utils.DateFormatter

class AddExerciseViewModel(
    private val insertSummaryUseCase: InsertSummaryUseCase,
    private val navigator: Navigator,
    private val dateFormatter: DateFormatter
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddExerciseState())
    val viewState: StateFlow<AddExerciseState> = _viewState

    private val _viewEvent = MutableSharedFlow<AddExerciseEvent>(extraBufferCapacity = 1)
    val viewEvent: SharedFlow<AddExerciseEvent> = _viewEvent

    private var pickedDate: LocalDateTime? = null

    fun onExerciseTitleChanged(title: String) {
        _viewState.value = _viewState.value.copy(exerciseTitle = title)
    }

    fun onExerciseNewResultChanged(title: String) {
        _viewState.value = _viewState.value.copy(newResult = title)
    }

    fun onAddNewResultClicked() {
        val currentResults = _viewState.value.results.toMutableList()
        val newResult = _viewState.value.newResult
        val newResultDate = pickedDate
        currentResults.add(ResultData(newResult, newResultDate))
        _viewState.value = _viewState.value.copy(results = currentResults)
    }

    fun onAddNewExerciseClicked() {
        val type = _viewState.value.exerciseTitle
        val results = _viewState.value.results
        insertSummaryUseCase(
            SummaryEntity(
                exerciseTitle = type,
                results = results,
                exerciseType = ExerciseType.GYM
            )
        )
        navigator.navigateUp()
    }

    fun onDateClicked() {
        _viewEvent.tryEmit(AddExerciseEvent.OpenCalendar)
    }

    fun onDatePicked(date: LocalDateTime) {
        val formattedDate = dateFormatter.formatDate(date)
        pickedDate = date
        _viewState.value = _viewState.value.copy(newResultDate = formattedDate)
    }

    fun onResultRemoved(resultIndex: Int) {
        val currentResults = _viewState.value.results.toMutableList()
        currentResults.removeAt(resultIndex)
        _viewState.value = _viewState.value.copy(results = currentResults)
    }

    fun onExerciseTypeClicked(type: ExerciseType) {
        _viewState.value = _viewState.value.copy(exerciseType = type)
    }

}