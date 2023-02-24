package pl.msiwak.multiplatform.ui.category

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.domain.summaries.GetCategoryUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class CategoryViewModel(
    id: Long,
    private val navigator: Navigator,
    private val getCategoryUseCase: GetCategoryUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(CategoryState())
    val viewState: StateFlow<CategoryState> = _viewState

    init {
        viewModelScope.launch {
            val category = getCategoryUseCase(id)
            _viewState.value = _viewState.value.copy(exerciseList = category.exercises)
        }
    }

    fun onAddExerciseClicked() {
        navigator.navigate(NavigationDirections.AddExercise)
    }

    fun onExerciseClicked(id: Long) {
        navigator.navigate(NavigationDirections.Exercise(id))
    }
}