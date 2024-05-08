package pl.msiwak.multiplatform.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.navigator.destination.BottomNavigationDestination
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.ui.commonComponent.OfflineBanner

@Composable
fun DashboardScreen(
    parentNavController: NavHostController,
    viewModel: DashboardViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    val items = listOf(
        NavDestination.DashboardDestination.SummaryDestination.NavSummaryGraphDestination,
//        DashboardNavigationDirections.Account(R.drawable.ic_account, stringResource(SR.strings.account)),
        NavDestination.DashboardDestination.SettingsDestination.NavSettingsGraphDestination
    )

    DashboardScreenContent(
        parentNavController = parentNavController,
        bottomNavigationProvider = viewModel.bottomNavigationProvider,
        viewState = viewState,
        items = items,
        onSignInUpClicked = {
            parentNavController.navigate(NavDestination.WelcomeDestination.NavWelcomeScreen.route)
        }
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun DashboardScreenContent(
    parentNavController: NavHostController,
    bottomNavigationProvider: BottomNavigationProvider,
    viewState: State<DashboardState>,
    items: List<BottomNavigationDestination>,
    onSignInUpClicked: () -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (viewState.value.isOfflineBannerVisible) {
                    OfflineBanner(onSignInUpClicked = onSignInUpClicked)
                }
                BottomNavigation(navController = navController, items = items)
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(paddingValues = it),
            navController = navController,
            startDestination = NavDestination.DashboardDestination.SummaryDestination.NavSummaryGraphDestination.graphRoute
        ) {
            with(bottomNavigationProvider) {
                summaryGraph.create(parentNavController, this@NavHost)
                settingsGraph.create(parentNavController, this@NavHost)
            }
        }
    }
}
