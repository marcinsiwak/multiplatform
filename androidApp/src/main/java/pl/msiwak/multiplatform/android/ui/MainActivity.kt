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
import pl.msiwak.multiplatform.android.ui.theme.AppTheme
import pl.msiwak.multiplatform.android.ui.units.UnitScreen
import pl.msiwak.multiplatform.android.ui.verifyEmail.VerifyEmailScreen
import pl.msiwak.multiplatform.android.ui.welcome.WelcomeScreen
import pl.msiwak.multiplatform.notifications.NotificationsService
import pl.msiwak.multiplatform.ui.main.MainViewModel
import pl.msiwak.multiplatform.ui.navigator.NavigationDirections

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by inject(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NotificationsService()
        setContent {
            val viewState = viewModel.viewState.collectAsState()
            installSplashScreen()
                .setKeepOnScreenCondition {
                    viewState.value.isLoading
                }

            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    LaunchedEffect(key1 = true) {
                        viewModel.mainNavigator.commands.collect {
                            when (it) {
                                is NavigationDirections.OpenStore -> openStore()
                                NavigationDirections.NavigateUp -> navController.navigateUp()
                                else -> navigate(navController, it)
                            }
                        }
                    }
                    NavHost(
                        navController = navController,
                        startDestination = viewState.value.directions.route
                    ) {
                        composable(NavigationDirections.Welcome().route) { WelcomeScreen() }
                        composable(NavigationDirections.Registration.route) { RegisterScreen() }
                        composable(NavigationDirections.Dashboard().route) { DashboardScreen() }
                        composable(NavigationDirections.AddCategory.route) { AddCategoryScreen() }
                        composable(NavigationDirections.Language.route) { LanguageScreen() }
                        composable(NavigationDirections.Unit.route) { UnitScreen() }
                        composable(NavigationDirections.ForceUpdate.route) { ForceUpdateScreen() }
                        composable(NavigationDirections.VerifyEmail.route) { VerifyEmailScreen() }
                        composable(
                            NavigationDirections.AddExercise().route,
                            arguments = listOf(
                                navArgument(NavigationDirections.AddExercise.BUNDLE_ARG_ID) {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry
                                .arguments
                                ?.getString(NavigationDirections.AddExercise.BUNDLE_ARG_ID) ?: ""
                            AddExerciseScreen(id)
                        }
                        composable(
                            NavigationDirections.CategoryDetails().route,
                            arguments = listOf(
                                navArgument(NavigationDirections.CategoryDetails.BUNDLE_ARG_ID) {
                                    type = NavType.StringType
                                }
                            )
                        ) { backStackEntry ->
                            val id = backStackEntry
                                .arguments
                                ?.getString(NavigationDirections.CategoryDetails.BUNDLE_ARG_ID)
                                ?: ""
                            CategoryScreen(id)
                        }
                    }
                }
            }
        }
    }

    private fun navigate(
        navController: NavController,
        command: NavigationDirections
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
