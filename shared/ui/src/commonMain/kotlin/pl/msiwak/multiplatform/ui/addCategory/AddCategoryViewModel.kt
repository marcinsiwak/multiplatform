package pl.msiwak.multiplatform.ui.addCategory

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.domain.summaries.CreateCategoryUseCase
import pl.msiwak.multiplatform.ui.navigator.Navigator

class AddCategoryViewModel(
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddCategoryState())
    val viewState: StateFlow<AddCategoryState> = _viewState.asStateFlow()

    fun onTypePicked(exerciseType: ExerciseType) {
        _viewState.value = _viewState.value.copy(exerciseType = exerciseType)
    }

    fun onCategoryNameChanged(name: String) {
        _viewState.value = _viewState.value.copy(name = name)
    }

    fun onSaveCategoryClicked() {
        val name = _viewState.value.name
        val exerciseType = _viewState.value.exerciseType
        viewModelScope.launch {
            createCategoryUseCase(Category(name = name, exerciseType = exerciseType))
            navigator.navigateUp()
        }
    }
}