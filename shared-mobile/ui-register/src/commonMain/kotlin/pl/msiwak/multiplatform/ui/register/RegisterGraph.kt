package pl.msiwak.multiplatform.ui.register

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.RegistrationDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class RegisterGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = RegistrationDestination.NavRegistrationGraphDestination.route,
            startDestination = RegistrationDestination.NavRegistrationScreen.route
        ) {
            composable(
                route = RegistrationDestination.NavRegistrationScreen.route
            ) {
                RegisterScreen(navController)
            }
        }
    }
}
