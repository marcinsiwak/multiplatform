package pl.msiwak.multiplatform.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.ui.commonComponent.Loader

@Composable
fun MainView(
    viewModel: MainViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()
    val navigationProvider = viewModel.navigationProvider

    AppTheme {
        val navController = rememberNavController()
        if (viewState.value.isLoading) {
            Loader()
        } else {
            NavHost(
                navController = navController,
                startDestination = viewState.value.directions.route
            ) {
                with(navigationProvider) {
                    welcomeGraph.create(navController, this@NavHost)
                    registerGraph.create(navController, this@NavHost)
                    termsGraph.create(navController, this@NavHost)
                    addCategoryGraph.create(navController, this@NavHost)
                    categoryGraph.create(navController, this@NavHost)
                    dashboardGraph.create(navController, this@NavHost)
                    addExerciseGraph.create(navController, this@NavHost)
                    unitGraph.create(navController, this@NavHost)
                    languageGraph.create(navController, this@NavHost)
                    forceUpdateGraph.create(navController, this@NavHost)
                    verifyEmailGraph.create(navController, this@NavHost)
                }
            }
        }
    }
}
