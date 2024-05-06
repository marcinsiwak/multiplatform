package pl.msiwak.multiplatform.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme

@Composable
fun MainView(
    viewModel: MainViewModel = koinInject()
) {

    val viewState = viewModel.viewState.collectAsState()
    val navigationProvider = viewModel.navigationProvider

    AppTheme {
        val navController = rememberNavController()
        LaunchedEffect(key1 = true) {
//            viewModel.mainNavigator.commands.collect {
//                when (it) {
//                    is NavigationDirections.OpenStore -> openStore()
//                    NavigationDirections.NavigateUp -> navController.navigateUp()
//                    else -> navigate(navController, it)
//                }
//            }
        }
        NavHost(
            navController = navController,
            startDestination = viewState.value.directions.route
        ) {
            with(navigationProvider) {
                welcomeGraph.create(navController, this@NavHost)
                registerGraph.create(navController, this@NavHost)
                addCategoryGraph.create(navController, this@NavHost)
                categoryGraph.create(navController, this@NavHost)
                dashboardGraph.create(navController, this@NavHost)
                addExerciseGraph.create(navController, this@NavHost)
                unitGraph.create(navController, this@NavHost)
                languageGraph.create(navController, this@NavHost)
                forceUpdateGraph.create(navController, this@NavHost)
                verifyEmailGraph.create(navController, this@NavHost)
            }
//            composable(NavigationDirections.Welcome().route) { WelcomeScreen(navController) }
//            composable(NavigationDirections.Registration.route) { RegisterScreen(navController) }
//            composable(NavigationDirections.Dashboard().route) { DashboardScreen(navController) }
//            composable(NavigationDirections.AddCategory.route) { AddCategoryScreen(navController) }
//            composable(NavigationDirections.Language.route) { LanguageScreen(navController) }
//            composable(NavigationDirections.Unit.route) { UnitScreen(navController) }
//            composable(NavigationDirections.ForceUpdate.route) { ForceUpdateScreen(navController) }
//            composable(NavigationDirections.VerifyEmail.route) { VerifyEmailScreen(navController) }
//            composable(
//                NavigationDirections.AddExercise().route,
//                arguments = listOf(
//                    navArgument(NavigationDirections.AddExercise.BUNDLE_ARG_ID) {
//                        type = NavType.StringType
//                    }
//                )
//            ) { backStackEntry ->
//                val id = backStackEntry
//                    .arguments
//                    ?.getString(NavigationDirections.AddExercise.BUNDLE_ARG_ID) ?: ""
//                AddExerciseScreen(navController, "")
//            }
//            composable(
//                NavigationDirections.CategoryDetails().route,
//                arguments = listOf(
//                    navArgument(NavigationDirections.CategoryDetails.BUNDLE_ARG_ID) {
//                        type = NavType.StringType
//                    }
//                )
//            ) { backStackEntry ->
//                val id = backStackEntry
//                    .arguments
//                    ?.getString(NavigationDirections.CategoryDetails.BUNDLE_ARG_ID)
//                    ?: ""
//                CategoryScreen(navController, "")
//            }
        }
    }
}
