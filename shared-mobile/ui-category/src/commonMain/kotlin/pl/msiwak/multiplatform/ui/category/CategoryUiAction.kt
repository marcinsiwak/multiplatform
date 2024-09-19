package pl.msiwak.multiplatform.ui.category

sealed class CategoryUiAction {
    data object OnConfirmClick : CategoryUiAction()
    data object OnDismissClicked : CategoryUiAction()
    data class OnExerciseTitleChanged(val name: String) : CategoryUiAction()
    data object OnAddExerciseClicked : CategoryUiAction()
    data object OnDialogClosed : CategoryUiAction()
    data class OnItemClick(val id: String) : CategoryUiAction()
    data class OnLongClick(val pos: Int) : CategoryUiAction()
    data object OnClick : CategoryUiAction()
}
