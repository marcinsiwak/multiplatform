package pl.msiwak.multiplatform.ui.verifyEmail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.VerifyEmailDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class VerifyEmailGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = VerifyEmailDestination.NavVerifyEmailGraphDestination.route,
            startDestination = VerifyEmailDestination.NavVerifyEmailScreen.route
        ) {
            composable(
                route = VerifyEmailDestination.NavVerifyEmailScreen.route
            ) {
                VerifyEmailScreen(navController)
            }
        }
    }
}
