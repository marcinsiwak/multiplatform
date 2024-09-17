package pl.msiwak.multiplatform.ui.addCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.domain.summaries.CreateCategoryUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class AddCategoryViewModel(
    private val createCategoryUseCase: CreateCategoryUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddCategoryState())
    val viewState: StateFlow<AddCategoryState> = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<AddCategoryEvent>()
    val viewEvent: SharedFlow<AddCategoryEvent> = _viewEvent.asSharedFlow()

    private val errorHandler = globalErrorHandler.handleError {
        _viewState.update { it.copy(isLoading = false) }
        false
    }

    fun onTypePicked(exerciseType: ExerciseType) {
        _viewState.value = _viewState.value.copy(exerciseType = exerciseType)
    }

    fun onCategoryNameChanged(name: String) {
        _viewState.value = _viewState.value.copy(name = name)
    }

    fun onSaveCategoryClicked() {
        val name = _viewState.value.name
        val exerciseType = _viewState.value.exerciseType
        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch(errorHandler) {
            createCategoryUseCase(Category(name = name, exerciseType = exerciseType))
            _viewState.update { it.copy(isLoading = false) }
            _viewEvent.emit(AddCategoryEvent.NavigateBack)
        }
    }
}
