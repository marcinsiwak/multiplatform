package pl.msiwak.multiplatform.ui.exercise

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.domain.summaries.GetSummaryUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertSummaryUseCase

class ExerciseViewModel(
    private val insertSummaryUseCase: InsertSummaryUseCase,
    private val getSummaryUseCase: GetSummaryUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(ExerciseState())
    val viewState: StateFlow<ExerciseState> = _viewState

    fun onInit(id: Long) {
        val summary = getSummaryUseCase(id)
        _viewState.value = _viewState.value.copy(
            exerciseTitle = summary.exerciseType,
            results = summary.results,
        )
    }

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