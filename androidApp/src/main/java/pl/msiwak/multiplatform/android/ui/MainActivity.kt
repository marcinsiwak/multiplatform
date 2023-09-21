package pl.msiwak.multiplatform.android.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
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
import pl.msiwak.multiplatform.android.ui.forceUpdate.ForceUpdateScreen
import pl.msiwak.multiplatform.android.ui.language.LanguageScreen
import pl.msiwak.multiplatform.android.ui.register.RegisterScreen
import pl.msiwak.multiplatform.android.ui.theme.BaseKmm_ProjectTheme
import pl.msiwak.multiplatform.android.ui.units.UnitScreen
import pl.msiwak.multiplatform.android.ui.verifyEmail.VerifyEmailScreen
import pl.msiwak.multiplatform.android.ui.welcome.WelcomeScreen
import pl.msiwak.multiplatform.ui.main.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewState = viewModel.viewState.collectAsState()
            installSplashScreen()
                .setKeepOnScreenCondition {
                    viewState.value.isLoading
                }

            BaseKmm_ProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    LaunchedEffect(key1 = true) {
                        viewModel.mainNavigator.commands.collect {
                            when (it) {
                                is pl.msiwak.multiplatform.ui.navigator.NavigationDirections.OpenStore -> openStore()
                                pl.msiwak.multiplatform.ui.navigator.NavigationDirections.NavigateUp -> navController.navigateUp()
                                else -> navigate(navController, it)
                            }
                        }
                    }
                    NavHost(
                        navController = navController,
                        startDestination = viewState.value.directions.route
                    ) {
                        composable(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.Welcome().route) { WelcomeScreen() }
                        composable(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.Registration.route) { RegisterScreen() }
                        composable(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.Dashboard().route) { DashboardScreen() }
                        composable(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.AddCategory.route) { AddCategoryScreen() }
                        composable(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.Language.route) { LanguageScreen() }
                        composable(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.Unit.route) { UnitScreen() }
                        composable(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.ForceUpdate.route) { ForceUpdateScreen() }
                        composable(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.VerifyEmail.route) { VerifyEmailScreen() }
                        composable(
                            pl.msiwak.multiplatform.ui.navigator.NavigationDirections.AddExercise().route,
                            arguments = listOf(
                                navArgument(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.AddExercise.BUNDLE_ARG_ID) {
                                    type = NavType.LongType
                                },
                            )
                        ) { backStackEntry ->
                            val id =
                                backStackEntry.arguments?.getLong(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.AddExercise.BUNDLE_ARG_ID)
                                    ?: 0
                            AddExerciseScreen(id)
                        }
                        composable(
                            pl.msiwak.multiplatform.ui.navigator.NavigationDirections.CategoryDetails().route,
                            arguments = listOf(
                                navArgument(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.CategoryDetails.BUNDLE_ARG_ID) {
                                    type = NavType.LongType
                                },
                            )
                        ) { backStackEntry ->
                            val id =
                                backStackEntry.arguments?.getLong(pl.msiwak.multiplatform.ui.navigator.NavigationDirections.CategoryDetails.BUNDLE_ARG_ID)
                                    ?: 0
                            CategoryScreen(id)
                        }
                    }
                }
            }
        }
    }

    private fun navigate(
        navController: NavController,
        command: pl.msiwak.multiplatform.ui.navigator.NavigationDirections
    ) {
        if (command.isInclusive) {
            navController.navigate(route = command.destination) {
                popUpTo(0)
            }

        } else {
            navController.navigate(route = command.destination)
        }
    }

    private fun openStore() {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("$URI_MARKET$packageName")))
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("$URI_STORE$packageName")
                )
            )
        }
    }

    companion object {
        private const val URI_MARKET = "market://details?id="
        private const val URI_STORE = "https://play.google.com/store/apps/details?id="
    }
}
