package pl.msiwak.multiplatform.ui.addCategory

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.domain.summaries.CreateCategoryUseCase
import pl.msiwak.multiplatform.navigator.Navigator
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class AddCategoryViewModel(
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val navigator: Navigator,
    private val globalErrorHandler: GlobalErrorHandler
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
        viewModelScope.launch(globalErrorHandler.handleError()) {
            _viewState.update { it.copy(isLoading = true) }
            createCategoryUseCase(Category(name = name, exerciseType = exerciseType))
            _viewState.update { it.copy(isLoading = false) }
            navigator.navigateUp()
        }
    }
}
