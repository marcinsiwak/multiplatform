package pl.msiwak.multiplatform.ui.summary

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.summaries.GetSummariesUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class SummaryViewModel(
    getSummariesUseCase: GetSummariesUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _summaryState = MutableStateFlow(SummaryState())
    val summaryState: StateFlow<SummaryState> = _summaryState

    init {
        val summaries = getSummariesUseCase()
        _summaryState.value = _summaryState.value.copy(summaries = summaries)
    }

    fun onAddExerciseClicked() {
        navigator.navigate(NavigationDirections.AddExercise)
    }

    fun onExerciseClicked(summaryId: Long) {
        navigator.navigate(NavigationDirections.Exercise(summaryId))
    }
}