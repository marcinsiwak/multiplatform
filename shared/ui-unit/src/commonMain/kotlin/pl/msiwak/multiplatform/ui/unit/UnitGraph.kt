package pl.msiwak.multiplatform.ui.unit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.UnitDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class UnitGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = UnitDestination.NavUnitGraphDestination.route,
            startDestination = UnitDestination.NavUnitScreen.route
        ) {
            composable(
                route = UnitDestination.NavUnitScreen.route
            ) {
                UnitScreen(navController)
            }
        }
    }
}
