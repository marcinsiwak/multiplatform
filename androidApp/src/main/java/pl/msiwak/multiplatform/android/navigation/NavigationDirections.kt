package pl.msiwak.multiplatform.android.navigation

import androidx.navigation.NamedNavArgument

sealed class NavigationDirections : NavigationCommand {
    object Welcome : NavigationDirections() {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "welcome"
    }
    object Registration : NavigationDirections() {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "registration"
    }
    object Login : NavigationDirections() {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "login"
    }
    object Dashboard : NavigationDirections() {
        override val arguments: List<NamedNavArgument> = emptyList()
        override val destination: String = "dashboard"
    }
}