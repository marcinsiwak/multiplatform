package pl.msiwak.multiplatform.ui.welcome

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.ARG_ACCESS_TOKEN_ID
import pl.msiwak.multiplatform.navigator.ARG_TOKEN_ID
import pl.msiwak.multiplatform.navigator.ARG_UUID_ID
import pl.msiwak.multiplatform.navigator.destination.NavDestination.WelcomeDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph
import pl.msiwak.multiplatform.ui.welcome.terms.TermsConfirmationScreen

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
            composable(
                route = WelcomeDestination.NavTermsConfirmationScreen.route,
                arguments = listOf(
                    navArgument(ARG_TOKEN_ID) {
                        type = NavType.StringType
                        defaultValue = ""
                    },
                    navArgument(ARG_ACCESS_TOKEN_ID) {
                        type = NavType.StringType
                        defaultValue = null
                        nullable = true
                    }
                )
            ) { backStackEntry ->
                TermsConfirmationScreen(
                    uuid = backStackEntry.arguments?.getString(ARG_UUID_ID) ?: "",
                    navController = navController
                )
            }
        }
    }
}
