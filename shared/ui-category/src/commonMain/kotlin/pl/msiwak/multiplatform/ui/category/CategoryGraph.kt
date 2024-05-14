package pl.msiwak.multiplatform.ui.category

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.destination.NavDestination.CategoryDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class CategoryGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = CategoryDestination.NavCategoryGraphDestination.route,
            startDestination = CategoryDestination.NavCategoryScreen.route
        ) {
            composable(
                route = CategoryDestination.NavCategoryScreen.route
            ) {
                CategoryScreen(navController, "")
            }
        }
    }
}
