package pl.msiwak.multiplatform.ui.dashboard

data class DashboardState(
    val isOfflineBannerVisible: Boolean = false,
    val selectedTabIndex: Int = 0,
    val initialTabIndex: Int = 0
)
