package pl.msiwak.multiplatform.ui.terms

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class TermsGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = NavDestination.TermsDestination.NavTermsGraphDestination.route,
            startDestination = NavDestination.TermsDestination.NavTermsScreen.route
        ) {
            composable(
                route = NavDestination.TermsDestination.NavTermsScreen.route
            ) {
                TermsScreen(navController)
            }
        }
    }
}
