package pl.msiwak.multiplatform.ui.navigator

sealed class DashboardNavigationDirections(val icon: Int, val title: String) : NavigationCommand {

    class Summary(icon: Int, title: String) : DashboardNavigationDirections(icon, title) {
        override val destination: String = "main"
    }

    class Account(icon: Int, title: String) : DashboardNavigationDirections(icon, title) {
        override val destination: String = "account"
    }

    class Settings(icon: Int, title: String) : DashboardNavigationDirections(icon, title) {
        override val destination: String = "settings"
    }

}
