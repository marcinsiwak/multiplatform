package pl.msiwak.multiplatform.ui.addExercise

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.domain.summaries.GetSummaryUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertSummaryUseCase
import pl.msiwak.multiplatform.ui.exercise.ExerciseState

class AddExerciseViewModel(
    private val insertSummaryUseCase: InsertSummaryUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddExerciseState())
    val viewState: StateFlow<AddExerciseState> = _viewState

    fun onExerciseTitleChanged(title: String) {
        _viewState.value = _viewState.value.copy(exerciseTitle = title)
    }

    fun onExerciseNewResultChanged(title: String) {
        _viewState.value = _viewState.value.copy(newResult = title)
    }

    fun onAddNewExerciseClicked() {
        val type = _viewState.value.exerciseTitle
        val results = _viewState.value.results
        insertSummaryUseCase(Summary(exerciseType = type, results = results))
    }
}