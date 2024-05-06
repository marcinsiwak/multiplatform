package pl.msiwak.multiplatform.ui.welcome

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.WelcomeDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class WelcomeGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = WelcomeDestination.NavWelcomeGraphDestination.route,
            startDestination = WelcomeDestination.NavWelcomeScreen.route
        ) {
            composable(
                route = WelcomeDestination.NavWelcomeScreen.route
            ) {
                WelcomeScreen(navController)
            }
        }
    }
}
