package pl.msiwak.multiplatform.ui.adminpanel

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.AdminPanelDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class AdminPanelGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = AdminPanelDestination.NavAdminPanelGraphDestination.route,
            startDestination = AdminPanelDestination.NavAdminPanelScreen.route
        ) {
            composable(
                route = AdminPanelDestination.NavAdminPanelScreen.route
            ) {
                AdminPanelScreen(navController)
            }
        }
    }
}
