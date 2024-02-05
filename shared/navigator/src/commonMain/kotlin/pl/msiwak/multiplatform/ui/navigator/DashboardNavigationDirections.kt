package pl.msiwak.multiplatform.ui.navigator

sealed class DashboardNavigationDirections(val icon: Int, val title: String) : NavigationCommand {
    override val destination: String
        get() = route

    override val isInclusive: Boolean
        get() = false

    class Summary(icon: Int, title: String) : DashboardNavigationDirections(icon, title) {
        override val route: String = "main"
    }

    class Account(icon: Int, title: String) : DashboardNavigationDirections(icon, title) {
        override val route: String = "account"
    }

    class Settings(icon: Int, title: String) : DashboardNavigationDirections(icon, title) {
        override val route: String = "settings"
    }
}
