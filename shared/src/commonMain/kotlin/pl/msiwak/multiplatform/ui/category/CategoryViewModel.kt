package pl.msiwak.multiplatform.ui.category

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.entity.SummaryData
import pl.msiwak.multiplatform.domain.summaries.GetCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertSummaryUseCase
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.Navigator

class CategoryViewModel(
    id: Long,
    private val navigator: Navigator,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val insertSummaryUseCase: InsertSummaryUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(CategoryState())
    val viewState: StateFlow<CategoryState> = _viewState

    private val categoryId: Long = id

    fun onInit() {
        viewModelScope.launch {
            val category = getCategoryUseCase(categoryId)
            _viewState.value = _viewState.value.copy(exerciseList = category.exercises)
        }
    }

    fun onAddNewExerciseClicked() {
        _viewState.value = _viewState.value.copy(isDialogVisible = true)
    }

    fun onExerciseClicked(id: Long) {
        navigator.navigate(NavigationDirections.AddExercise(id))
    }

    fun onAddExerciseNameChanged(name: String) {
        _viewState.value = _viewState.value.copy(newExerciseName = name)
    }

    fun onAddExerciseClicked() {
        _viewState.value = _viewState.value.copy(isDialogVisible = false)
        viewModelScope.launch {
            val exerciseName = _viewState.value.newExerciseName
            val id = insertSummaryUseCase(SummaryData(categoryId = categoryId, exerciseTitle = exerciseName))
            navigator.navigate(NavigationDirections.AddExercise(id))
        }
    }

    fun onDialogClosed() {
        _viewState.value = _viewState.value.copy(isDialogVisible = false)
    }
}
