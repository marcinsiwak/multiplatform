package pl.msiwak.multiplatform.ui.summary

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.summaries.GetSummariesUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class SummaryViewModel(
    private val getSummariesUseCase: GetSummariesUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _summaryState = MutableStateFlow(SummaryState())
    val summaryState: StateFlow<SummaryState> = _summaryState

    fun onInit() {
        viewModelScope.launch {
            val summaries = getSummariesUseCase()
            _summaryState.value = _summaryState.value.copy(summaries = summaries)
        }
    }

    fun onAddExerciseClicked() {
        navigator.navigate(NavigationDirections.AddExercise)
    }

    fun onExerciseClicked(summaryId: Long) {
        navigator.navigate(NavigationDirections.Exercise(summaryId))
    }
}