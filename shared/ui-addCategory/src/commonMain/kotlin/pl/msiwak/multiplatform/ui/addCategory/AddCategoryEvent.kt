package pl.msiwak.multiplatform.ui.addCategory

sealed class AddCategoryEvent {
    data object NavigateBack: AddCategoryEvent()
}