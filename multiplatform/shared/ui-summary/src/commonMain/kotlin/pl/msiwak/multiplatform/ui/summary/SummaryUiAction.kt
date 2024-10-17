package pl.msiwak.multiplatform.ui.summary

sealed class SummaryUiAction {
    data object OnCategoryRemoved : SummaryUiAction()
    data object OnPopupDismissed : SummaryUiAction()
    data class OnCategoryClicked(val id: String) : SummaryUiAction()
    data class OnCategoryLongClicked(val pos: Int) : SummaryUiAction()
    data object OnAddCategoryClicked : SummaryUiAction()
}
