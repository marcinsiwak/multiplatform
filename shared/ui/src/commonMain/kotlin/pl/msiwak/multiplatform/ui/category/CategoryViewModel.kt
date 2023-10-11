package pl.msiwak.multiplatform.ui.category

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
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
    val viewState: StateFlow<CategoryState> = _viewState

    private val categoryId: String = id
    private var exerciseToRemovePosition: Int? = null
    private val exercises: MutableList<Exercise> = mutableListOf()

    private val errorHandler = globalErrorHandler.handleError()

    init {
        viewModelScope.launch(errorHandler) {
            observeCategoryUseCase(categoryId).collect {
                exercises.clear()
                exercises.addAll(it.exercises)
                _viewState.value =
                    _viewState.value.copy(
                        categoryName = it.name,
                        exerciseType = it.exerciseType,
                        exerciseList = it.exercises
                    )
            }
        }
    }

    fun onResume() {
        viewModelScope.launch(errorHandler) {
            downloadCategoryUseCase(categoryId)
        }
    }

    fun onAddNewExerciseClicked() {
        _viewState.value = _viewState.value.copy(isDialogVisible = true)
    }

    fun onExerciseClicked(id: String) {
        navigator.navigate(NavigationDirections.AddExercise(id))
    }

    fun onAddExerciseNameChanged(name: String) {
        _viewState.value = _viewState.value.copy(newExerciseName = name)
    }

    fun onAddExerciseClicked() {
        _viewState.value = _viewState.value.copy(isDialogVisible = false)
        viewModelScope.launch {
            val exerciseName = _viewState.value.newExerciseName
            val exerciseType = _viewState.value.exerciseType
            addExerciseUseCase(
                Exercise(
                    categoryId = categoryId,
                    exerciseTitle = exerciseName,
                    exerciseType = exerciseType,
                    creationDate = Clock.System.now()
                )
            )
            //todo handle offline
//            navigator.navigate(NavigationDirections.AddExercise(id))
        }
    }

    fun onDialogClosed() {
        _viewState.value = _viewState.value.copy(isDialogVisible = false)
    }

    fun onResultLongClicked(resultIndex: Int) {
        exerciseToRemovePosition = resultIndex
        _viewState.value = _viewState.value.copy(isRemoveExerciseDialogVisible = true)
    }

    fun onResultRemoved() {
        exerciseToRemovePosition?.let {
            val exercise = exercises[it]
            exercises.removeAt(it)
            _viewState.value = _viewState.value.copy(exerciseList = exercises)

            viewModelScope.launch {
                removeExerciseUseCase(exercise)
                _viewState.value = _viewState.value.copy(isRemoveExerciseDialogVisible = false)
            }
        }
    }

    fun onPopupDismissed() {
        _viewState.value = _viewState.value.copy(isRemoveExerciseDialogVisible = false)
    }
}
