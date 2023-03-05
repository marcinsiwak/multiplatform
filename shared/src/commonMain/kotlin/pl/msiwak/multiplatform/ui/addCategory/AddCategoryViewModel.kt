package pl.msiwak.multiplatform.ui.addCategory

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.domain.summaries.InsertCategoryUseCase
import pl.msiwak.multiplatform.domain.summaries.RemoveCategoryUseCase
import pl.msiwak.multiplatform.ui.navigator.Navigator

class AddCategoryViewModel(
    private val insertCategoryUseCase: InsertCategoryUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _viewState = MutableStateFlow(AddCategoryState())
    val viewState: StateFlow<AddCategoryState> = _viewState

    fun onTypePicked(exerciseType: ExerciseType) {
        _viewState.value = _viewState.value.copy(exerciseType = exerciseType)
    }

    fun onExerciseName(name: String) {
        _viewState.value = _viewState.value.copy(name = name)
    }

    fun onSaveCategoryClicked() {
        val name = _viewState.value.name
        val exerciseType = _viewState.value.exerciseType
        viewModelScope.launch {
            insertCategoryUseCase(CategoryData(name = name, exerciseType = exerciseType))
            navigator.navigateUp()
        }
    }
}