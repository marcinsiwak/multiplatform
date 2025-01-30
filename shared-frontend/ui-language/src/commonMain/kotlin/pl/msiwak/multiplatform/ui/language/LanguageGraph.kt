package pl.msiwak.multiplatform.ui.language

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.LanguageDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class LanguageGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = LanguageDestination.NavLanguageGraphDestination.route,
            startDestination = LanguageDestination.NavLanguageScreen.route
        ) {
            composable(
                route = LanguageDestination.NavLanguageScreen.route
            ) {
                LanguageScreen(navController)
            }
        }
    }
}
