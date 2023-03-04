package pl.msiwak.multiplatform.ui.summary

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.summaries.GetCategoriesUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoriesUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class SummaryViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val observeCategoriesUseCase: ObserveCategoriesUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _summaryState = MutableStateFlow(SummaryState())
    val summaryState: StateFlow<SummaryState> = _summaryState

    init {
        viewModelScope.launch {
            val categories = getCategoriesUseCase()
//            _summaryState.value = _summaryState.value.copy(categories = categories)
            observeCategoriesUseCase().collect {
                _summaryState.value = _summaryState.value.copy(categories = it)

            }
        }
    }

    fun onAddExerciseClicked() {
//        navigator.navigate(NavigationDirections.AddExercise())
    }

    fun onExerciseClicked(id: Long) {
        navigator.navigate(NavigationDirections.Category(id))
    }
}