package pl.msiwak.multiplatform.ui.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.DashboardDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class DashboardGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = DashboardDestination.NavDashboardGraphDestination.route,
            startDestination = DashboardDestination.NavDashboardScreen.route
        ) {
            composable(
                route = DashboardDestination.NavDashboardScreen.route
            ) {
                DashboardScreen(navController)
            }
        }
    }
}
