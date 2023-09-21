package pl.msiwak.multiplatform.core.ui.category

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.core.ViewModel
import pl.msiwak.multiplatform.domain.summaries.GetCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertExerciseUseCase
import pl.msiwak.multiplatform.domain.summaries.ObserveCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveExerciseUseCase
import pl.msiwak.multiplatform.core.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.core.ui.navigator.Navigator

class CategoryViewModel(
    id: String,
    private val navigator: Navigator,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val insertExerciseUseCase: InsertExerciseUseCase,
    private val removeExerciseUseCase: RemoveExerciseUseCase,
    observeCategoryUseCase: ObserveCategoryUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(CategoryState())
    val viewState: StateFlow<CategoryState> = _viewState

    private val categoryId: String = id
    private var exerciseToRemovePosition: Int? = null
    private val exercises: MutableList<Exercise> = mutableListOf()

    init {
        viewModelScope.launch {
            observeCategoryUseCase(categoryId).collect {
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
//            val id = insertExerciseUseCase(
//                Exercise(
//                    categoryId = categoryId,
//                    exerciseTitle = exerciseName,
//                    exerciseType = exerciseType
//                )
//            )
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
            val id = exercises[it].id
            exercises.removeAt(it)
            _viewState.value = _viewState.value.copy(exerciseList = exercises)

            viewModelScope.launch {
                removeExerciseUseCase(id)
                _viewState.value = _viewState.value.copy(isRemoveExerciseDialogVisible = false)
            }
        }
    }

    fun onPopupDismissed() {
        _viewState.value = _viewState.value.copy(isRemoveExerciseDialogVisible = false)
    }
}
