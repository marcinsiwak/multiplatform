package pl.msiwak.multiplatform.ui.dashboard

sealed class DashboardUiAction {
    data object OnSignInUpClicked : DashboardUiAction()
    data class OnTabChanges(val pos: Int) : DashboardUiAction()
}
