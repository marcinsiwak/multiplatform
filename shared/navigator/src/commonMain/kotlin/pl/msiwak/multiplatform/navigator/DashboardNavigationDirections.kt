@file:OptIn(ExperimentalResourceApi::class)

package pl.msiwak.multiplatform.navigator

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

sealed class DashboardNavigationDirections(val icon: DrawableResource, val title: StringResource) : NavigationCommand {
    override val destination: String
        get() = route

    override val isInclusive: Boolean
        get() = false

    class Summary(icon: DrawableResource, title: StringResource) : DashboardNavigationDirections(icon, title) {
        override val route: String = "main"
    }

    class Account(icon: DrawableResource, title: StringResource) : DashboardNavigationDirections(icon, title) {
        override val route: String = "account"
    }

    class Settings(icon: DrawableResource, title: StringResource) : DashboardNavigationDirections(icon, title) {
        override val route: String = "settings"
    }
}
