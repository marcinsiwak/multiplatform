package pl.msiwak.multiplatform.ui.addExercise

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.navigator.ARG_EXERCISE_ID
import pl.msiwak.multiplatform.navigator.destination.NavDestination.AddExerciseDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph

class AddExerciseGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = AddExerciseDestination.NavAddExerciseGraphDestination.route,
            startDestination = AddExerciseDestination.NavAddExerciseScreen.route
        ) {
            composable(
                route = AddExerciseDestination.NavAddExerciseScreen.route
            ) { backStackEntry ->
                AddExerciseScreen(
                    navController,
                    backStackEntry.arguments?.getString(ARG_EXERCISE_ID) ?: ""
                )
            }
        }
    }
}
