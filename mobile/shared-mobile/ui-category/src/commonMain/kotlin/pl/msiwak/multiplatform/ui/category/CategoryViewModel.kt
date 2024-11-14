package pl.msiwak.multiplatform.ui.category

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
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.domain.summaries.AddExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveExerciseUseCase
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class CategoryViewModel(
    private val addExerciseUseCase: AddExerciseUseCase,
    private val removeExerciseUseCase: RemoveExerciseUseCase,
    private val downloadCategoryUseCase: DownloadCategoryUseCase,
    private val observeCategoryUseCase: ObserveCategoryUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(CategoryState())
    val viewState: StateFlow<CategoryState> = _viewState.asStateFlow()

    private val _viewEvent = MutableSharedFlow<CategoryEvent>()
    val viewEvent: SharedFlow<CategoryEvent> = _viewEvent.asSharedFlow()

    private lateinit var categoryId: String
    private var exerciseToRemovePosition: Int? = null
    private var exercises: List<Exercise> = emptyList()

    private val errorHandler = globalErrorHandler.handleError {
        _viewState.update { it.copy(isLoading = false) }
        false
    }

    fun onInit(id: String) {
        categoryId = id
        viewModelScope.launch(errorHandler) {
            observeCategoryUseCase(categoryId).collect { category ->
                exercises = category.exercises
                _viewState.update {
                    it.copy(
                        categoryName = category.name,
                        exerciseType = category.exerciseType,
                        exerciseList = category.exercises
                    )
                }
            }
        }
    }

    fun onResume() {
        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch(errorHandler) {
            downloadCategoryUseCase(categoryId)
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    fun onUiAction(action: CategoryUiAction) {
        when (action) {
            CategoryUiAction.OnConfirmClick -> onExerciseRemoved()
            CategoryUiAction.OnDismissClicked -> onPopupDismissed()
            is CategoryUiAction.OnExerciseTitleChanged -> onAddExerciseNameChanged(action.name)
            CategoryUiAction.OnAddExerciseClicked -> onAddExerciseClicked()
            CategoryUiAction.OnDialogClosed -> onDialogClosed()
            is CategoryUiAction.OnItemClick -> openExercise(action.id)
            is CategoryUiAction.OnLongClick -> onResultLongClicked(action.pos)
            CategoryUiAction.OnClick -> onAddNewExerciseClicked()
        }
    }

    private fun openExercise(id: String) {
        viewModelScope.launch {
            _viewEvent.emit(CategoryEvent.NavigateToAddExercise(id))
        }
    }

    private fun onAddNewExerciseClicked() {
        _viewState.update { it.copy(isDialogVisible = true) }
    }

    private fun onAddExerciseNameChanged(name: String) {
        _viewState.update { it.copy(newExerciseName = name) }
    }

    private fun onAddExerciseClicked() {
        _viewState.update { it.copy(isDialogVisible = false, isLoading = true) }

        viewModelScope.launch(errorHandler) {
            val id = addExerciseUseCase(
                Exercise(
                    categoryId = categoryId,
                    exerciseTitle = viewState.value.newExerciseName,
                    exerciseType = viewState.value.exerciseType,
                    creationDate = Clock.System.now()
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                )
            )
            _viewState.update { it.copy(isLoading = false) }
            _viewEvent.emit(CategoryEvent.NavigateToAddExercise(id))
        }
    }

    private fun onDialogClosed() {
        _viewState.update { it.copy(isDialogVisible = false) }
    }

    private fun onResultLongClicked(resultIndex: Int) {
        exerciseToRemovePosition = resultIndex
        _viewState.update { it.copy(isRemoveExerciseDialogVisible = true) }
    }

    private fun onExerciseRemoved() {
        _viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch(errorHandler) {
            exerciseToRemovePosition?.let { pos ->
                val exercise = exercises[pos]

                removeExerciseUseCase(exercise)
                _viewState.update {
                    it.copy(
                        isRemoveExerciseDialogVisible = false,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun onPopupDismissed() {
        _viewState.update { it.copy(isRemoveExerciseDialogVisible = false) }
    }
}
