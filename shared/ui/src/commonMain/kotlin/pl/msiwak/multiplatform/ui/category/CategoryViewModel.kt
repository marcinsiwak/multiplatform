package pl.msiwak.multiplatform.ui.category

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.domain.summaries.AddExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.DownloadCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveExerciseUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator
import pl.msiwak.multiplatform.utils.errorHandler.GlobalErrorHandler

class CategoryViewModel(
    id: String,
    private val navigator: Navigator,
    private val addExerciseUseCase: AddExerciseUseCase,
    private val removeExerciseUseCase: RemoveExerciseUseCase,
    private val downloadCategoryUseCase: DownloadCategoryUseCase,
    observeCategoryUseCase: ObserveCategoryUseCase,
    globalErrorHandler: GlobalErrorHandler
) : ViewModel() {

    private val _viewState = MutableStateFlow(CategoryState())
    val viewState: StateFlow<CategoryState> = _viewState.asStateFlow()

    private val categoryId: String = id
    private var exerciseToRemovePosition: Int? = null
    private val exercises: MutableList<Exercise> = mutableListOf()

    private val errorHandler = globalErrorHandler.handleError()

    init {
        viewModelScope.launch(errorHandler) {
            observeCategoryUseCase(categoryId).collect { category ->
                exercises.clear()
                exercises.addAll(category.exercises)
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
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }
            downloadCategoryUseCase(categoryId)
            _viewState.update { it.copy(isLoading = false) }
        }
    }

    fun onAddNewExerciseClicked() {
        _viewState.update { it.copy(isDialogVisible = true) }
    }

    fun onExerciseClicked(id: String) {
        navigator.navigate(NavigationDirections.AddExercise(id))
    }

    fun onAddExerciseNameChanged(name: String) {
        _viewState.update { it.copy(newExerciseName = name) }
    }

    fun onAddExerciseClicked() {
        _viewState.update { it.copy(isDialogVisible = false, isLoading = true) }

        viewModelScope.launch(errorHandler) {
            val exerciseName = _viewState.value.newExerciseName
            val exerciseType = _viewState.value.exerciseType
            val id = addExerciseUseCase(
                Exercise(
                    categoryId = categoryId,
                    exerciseTitle = exerciseName,
                    exerciseType = exerciseType,
                    creationDate = Clock.System.now()
                        .toLocalDateTime(TimeZone.currentSystemDefault())
                )
            )
            _viewState.update { it.copy(isLoading = false) }
            navigator.navigate(NavigationDirections.AddExercise(id))
        }
    }

    fun onDialogClosed() {
        _viewState.update { it.copy(isDialogVisible = false) }
    }

    fun onResultLongClicked(resultIndex: Int) {
        exerciseToRemovePosition = resultIndex
        _viewState.update { it.copy(isRemoveExerciseDialogVisible = true) }
    }

    fun onResultRemoved() {
        viewModelScope.launch(errorHandler) {
            _viewState.update { it.copy(isLoading = true) }

            exerciseToRemovePosition?.let { pos ->
                val exercise = exercises[pos]
                exercises.removeAt(pos)

                removeExerciseUseCase(exercise)
                _viewState.update { it.copy(isRemoveExerciseDialogVisible = false, isLoading = false) }
            }
        }
    }

    fun onPopupDismissed() {
        _viewState.update { it.copy(isRemoveExerciseDialogVisible = false) }
    }
}
