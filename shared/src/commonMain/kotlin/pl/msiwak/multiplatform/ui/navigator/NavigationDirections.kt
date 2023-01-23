package pl.msiwak.multiplatform.ui.navigator


sealed class NavigationDirections : NavigationCommand {
    object Welcome : NavigationDirections() {
        override val destination: String = "welcome"
    }
    object Registration : NavigationDirections() {
        override val destination: String = "registration"
    }
    object Login : NavigationDirections() {
        override val destination: String = "login"
    }
    object Dashboard : NavigationDirections() {
        override val destination: String = "dashboard"
    }
}