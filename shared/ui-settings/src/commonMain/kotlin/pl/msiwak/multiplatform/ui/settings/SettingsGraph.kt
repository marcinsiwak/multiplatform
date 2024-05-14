package pl.msiwak.multiplatform.ui.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.DashboardDestination.SettingsDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class SettingsGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = SettingsDestination.NavSettingsGraphDestination.graphRoute,
            startDestination = SettingsDestination.NavSettingsScreen.route
        ) {
            composable(
                route = SettingsDestination.NavSettingsScreen.route
            ) {
                SettingsScreen(navController)
            }
        }
    }
}
