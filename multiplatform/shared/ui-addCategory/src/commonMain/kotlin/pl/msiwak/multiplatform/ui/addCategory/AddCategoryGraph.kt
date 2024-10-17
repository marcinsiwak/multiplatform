package pl.msiwak.multiplatform.ui.addCategory

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.AddCategoryDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class AddCategoryGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = AddCategoryDestination.NavAddCategoryGraphDestination.route,
            startDestination = AddCategoryDestination.NavAddCategoryScreen.route
        ) {
            composable(
                route = AddCategoryDestination.NavAddCategoryScreen.route
            ) {
                AddCategoryScreen(navController)
            }
        }
    }
}
