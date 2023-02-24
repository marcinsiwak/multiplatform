package pl.msiwak.multiplatform.ui.addExercise

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
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.entity.SummaryData
import pl.msiwak.multiplatform.domain.summaries.FormatDateUseCase
import pl.msiwak.multiplatform.domain.summaries.FormatResultsUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertSummaryUseCase
import pl.msiwak.multiplatform.ui.navigator.Navigator

class AddExerciseViewModel(
    private val insertSummaryUseCase: InsertSummaryUseCase,
    private val navigator: Navigator,
    private val formatDateUseCase: FormatDateUseCase,
    private val formatResultsUseCase: FormatResultsUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddExerciseState())
    val viewState: StateFlow<AddExerciseState> = _viewState

    private val _viewEvent = MutableSharedFlow<AddExerciseEvent>(extraBufferCapacity = 1)
    val viewEvent: SharedFlow<AddExerciseEvent> = _viewEvent

    private var pickedDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    private val currentResults: MutableList<ResultData> = mutableListOf()

    fun onExerciseTitleChanged(title: String) {
        _viewState.value = _viewState.value.copy(exerciseTitle = title)
    }

    fun onExerciseNewResultChanged(title: String) {
        _viewState.value = _viewState.value.copy(newResult = title)
    }

    fun onAddNewResultClicked() {
        _viewState.value = _viewState.value.copy(isResultFieldEnabled = true)
//        val newResult = _viewState.value.newResult
//        val newResultDate = pickedDate
//        currentResults.add(ResultData(newResult, newResultDate))
//        val results = formatResultsUseCase(currentResults)
//        _viewState.value = _viewState.value.copy(results = results)
    }

    fun onAddNewExerciseClicked() {
        viewModelScope.launch {
            val type = _viewState.value.exerciseTitle
            val results = currentResults
            val exerciseType = _viewState.value.exerciseType
            insertSummaryUseCase(
                SummaryData(
                    exerciseTitle = type,
                    results = results,
                    exerciseType = exerciseType
                )
            )
            navigator.navigateUp()
        }
    }

    fun onDateClicked() {
        _viewEvent.tryEmit(AddExerciseEvent.OpenCalendar)
    }

    fun onDatePicked(date: LocalDateTime) {
        val formattedDate = formatDateUseCase(date)
        pickedDate = date
        _viewState.value = _viewState.value.copy(newResultDate = formattedDate)
    }

    fun onResultRemoved(resultIndex: Int) {
        currentResults.removeAt(resultIndex)
        val results = formatResultsUseCase(currentResults)
        _viewState.value = _viewState.value.copy(results = results)
    }

    fun onExerciseTypeClicked(type: ExerciseType) {
        _viewState.value = _viewState.value.copy(exerciseType = type)
    }

}