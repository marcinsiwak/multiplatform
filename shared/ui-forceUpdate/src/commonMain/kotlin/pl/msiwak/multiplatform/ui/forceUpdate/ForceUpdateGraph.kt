package pl.msiwak.multiplatform.ui.forceUpdate

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.ForceUpdateDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class ForceUpdateGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = ForceUpdateDestination.NavForceUpdateGraphDestination.route,
            startDestination = ForceUpdateDestination.NavForceUpdateScreen.route
        ) {
            composable(
                route = ForceUpdateDestination.NavForceUpdateScreen.route
            ) {
                ForceUpdateScreen()
            }
        }
    }
}
