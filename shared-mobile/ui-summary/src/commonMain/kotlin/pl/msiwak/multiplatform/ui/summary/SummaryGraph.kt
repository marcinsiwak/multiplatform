package pl.msiwak.multiplatform.ui.summary

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.DashboardDestination.SummaryDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class SummaryGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = SummaryDestination.NavSummaryGraphDestination.graphRoute,
            startDestination = SummaryDestination.NavSummaryScreen.route
        ) {
            composable(
                route = SummaryDestination.NavSummaryScreen.route
            ) {
                SummaryScreen(navController)
            }
        }
    }
}
