package pl.msiwak.multiplatform.ui.exercise

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.extenstion.deserializeToObject
import pl.msiwak.multiplatform.navigator.ARG_EXERCISE_ID
import pl.msiwak.multiplatform.navigator.ARG_EXERCISE_TYPE
import pl.msiwak.multiplatform.navigator.destination.NavDestination.ExerciseDestination
import pl.msiwak.multiplatform.navigator.graph.NavigationNestedGraph
import pl.msiwak.multiplatform.ui.exercise.chart.ExerciseChartScreen

class ExerciseGraph : NavigationNestedGraph {

    override fun create(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            route = ExerciseDestination.NavExerciseGraphDestination.route,
            startDestination = ExerciseDestination.NavExerciseScreen.route
        ) {
            composable(
                route = ExerciseDestination.NavExerciseScreen.route
            ) { backStackEntry ->
                ExerciseScreen(
                    navController,
                    backStackEntry.arguments?.getString(ARG_EXERCISE_ID) ?: ""
                )
            }
            composable(
                route = ExerciseDestination.NavExerciseChartScreen.route
            ) { backStackEntry ->
                ExerciseChartScreen(
                    exerciseId = backStackEntry.arguments?.getString(ARG_EXERCISE_ID) ?: "",
                    exerciseType = backStackEntry.arguments?.getString(ARG_EXERCISE_TYPE)
                        ?.deserializeToObject() ?: ExerciseType.GYM,
                    navController = navController
                )
            }
        }
    }
}
