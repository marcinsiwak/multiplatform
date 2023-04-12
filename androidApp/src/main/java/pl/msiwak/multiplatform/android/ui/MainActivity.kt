package pl.msiwak.multiplatform.android.ui

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.android.ui.addCategory.AddCategoryScreen
import pl.msiwak.multiplatform.android.ui.addExercise.AddExerciseScreen
import pl.msiwak.multiplatform.android.ui.category.CategoryScreen
import pl.msiwak.multiplatform.android.ui.dashboard.DashboardScreen
import pl.msiwak.multiplatform.android.ui.language.LanguageScreen
import pl.msiwak.multiplatform.android.ui.login.LoginScreen
import pl.msiwak.multiplatform.android.ui.register.RegisterScreen
import pl.msiwak.multiplatform.android.ui.theme.BaseKmm_ProjectTheme
import pl.msiwak.multiplatform.android.ui.welcome.WelcomeScreen
import pl.msiwak.multiplatform.ui.main.MainViewModel
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BaseKmm_ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavigationDirections.Dashboard.route
                    ) {
                        composable(NavigationDirections.Welcome.route) { WelcomeScreen() }
                        composable(NavigationDirections.Registration.route) { RegisterScreen() }
                        composable(NavigationDirections.Login.route) { LoginScreen() }
                        composable(NavigationDirections.Dashboard.route) { DashboardScreen() }
                        composable(NavigationDirections.AddCategory.route) { AddCategoryScreen() }
                        composable(NavigationDirections.Language.route) { LanguageScreen() }
                        composable(
                            NavigationDirections.AddExercise().route, arguments = listOf(
                                navArgument(NavigationDirections.AddExercise.BUNDLE_ARG_ID) {
                                    type = NavType.LongType
                                },
                            )
                        ) { backStackEntry ->
                            val id =
                                backStackEntry.arguments?.getLong(NavigationDirections.AddExercise.BUNDLE_ARG_ID)
                                    ?: 0
                            AddExerciseScreen(id)
                        }
                        composable(
                            NavigationDirections.Category().route, arguments = listOf(
                                navArgument(NavigationDirections.Category.BUNDLE_ARG_ID) {
                                    type = NavType.LongType
                                },
                            )
                        ) { backStackEntry ->
                            val id =
                                backStackEntry.arguments?.getLong(NavigationDirections.Category.BUNDLE_ARG_ID)
                                    ?: 0
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }


    }
}
