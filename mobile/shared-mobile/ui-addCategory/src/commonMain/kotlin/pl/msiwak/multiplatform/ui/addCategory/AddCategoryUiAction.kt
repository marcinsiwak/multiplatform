package pl.msiwak.multiplatform.ui.addCategory

import pl.msiwak.multiplatform.commonObject.ExerciseType

sealed class AddCategoryUiAction {
    data class OnCategoryNameChanged(val name: String) : AddCategoryUiAction()
    data class OnTypePicked(val type: ExerciseType) : AddCategoryUiAction()
    data object OnSaveCategoryClicked : AddCategoryUiAction()
}
