package pl.msiwak.multiplatform.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.android.ui.addExercise.AddExerciseScreen
import pl.msiwak.multiplatform.android.ui.category.CategoryScreen
import pl.msiwak.multiplatform.android.ui.dashboard.DashboardScreen
import pl.msiwak.multiplatform.android.ui.exercise.ExerciseScreen
import pl.msiwak.multiplatform.android.ui.login.LoginScreen
import pl.msiwak.multiplatform.android.ui.register.RegisterScreen
import pl.msiwak.multiplatform.android.ui.theme.BaseKmm_ProjectTheme
import pl.msiwak.multiplatform.android.ui.welcome.WelcomeScreen
import pl.msiwak.multiplatform.ui.main.MainViewModel
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections.Category
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections.Exercise

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseKmm_ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavigationDirections.Dashboard.route
                    ) {
                        composable(NavigationDirections.Welcome.route) { WelcomeScreen() }
                        composable(NavigationDirections.Registration.route) { RegisterScreen() }
                        composable(NavigationDirections.Login.route) { LoginScreen() }
                        composable(NavigationDirections.AddExercise.route) { AddExerciseScreen() }
                        composable(NavigationDirections.Dashboard.route) { DashboardScreen() }
                        composable(
                            Exercise().route,
                            arguments = listOf(
                                navArgument(Exercise.BUNDLE_ARG_ID) { type = NavType.LongType },
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getLong(Exercise.BUNDLE_ARG_ID) ?: 0
                            ExerciseScreen(id)
                        }
                        composable(
                            Category().route,
                            arguments = listOf(
                                navArgument(Category.BUNDLE_ARG_ID) { type = NavType.LongType },
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getLong(Category.BUNDLE_ARG_ID) ?: 0
                            CategoryScreen(id)
                        }
                    }

                    viewModel.mainNavigator.commands.watch {
                        when (it) {
                            NavigationDirections.NavigateUp -> navController.navigateUp()
                            else -> {
                                if (it.destination.isNotEmpty()) {
                                    navController.navigate(route = it.destination)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
