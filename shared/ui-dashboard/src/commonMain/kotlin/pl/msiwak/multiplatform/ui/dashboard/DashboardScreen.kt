package pl.msiwak.multiplatform.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.navigator.destination.BottomNavigationDestination
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.navigator.destination.NavDestination.DashboardDestination
import pl.msiwak.multiplatform.ui.commonComponent.OfflineBanner

@Composable
fun DashboardScreen(
    parentNavController: NavHostController,
    viewModel: DashboardViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    val items = listOf(
        DashboardDestination.SummaryDestination.NavSummaryGraphDestination,
        DashboardDestination.SettingsDestination.NavSettingsGraphDestination
    )

    DashboardScreenContent(
        parentNavController = parentNavController,
        bottomNavigationProvider = viewModel.bottomNavigationProvider,
        viewState = viewState,
        items = items,
        onUiAction = {
            when (it) {
                DashboardUiAction.OnSignInUpClicked -> parentNavController.navigate(NavDestination.WelcomeDestination.NavWelcomeScreen.route)
                else -> viewModel.onUiAction(it)
            }
        }
    )
}

@Composable
private fun DashboardScreenContent(
    parentNavController: NavHostController,
    bottomNavigationProvider: BottomNavigationProvider,
    viewState: State<DashboardState>,
    items: List<BottomNavigationDestination>,
    onUiAction: (DashboardUiAction) -> Unit
) {
    val navController = rememberNavController()

    val selectedTabDestination = items[viewState.value.selectedTabIndex]

    val initialTabDestination = remember {
        items[viewState.value.initialTabIndex]
    }

    val navigationSelectedItem by navController.currentDashboardDirectionAsState(
        selectedTabDestination
    )

    LaunchedEffect(key1 = navigationSelectedItem) {
        val selectedTabIndex = items.indexOf(navigationSelectedItem)
        onUiAction(DashboardUiAction.OnTabChanges(selectedTabIndex))
    }

    Scaffold(
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (viewState.value.isOfflineBannerVisible) {
                    OfflineBanner(onSignInUpClicked = { onUiAction(DashboardUiAction.OnSignInUpClicked) })
                }
                BottomNavigation(
                    initialTabDestination = initialTabDestination,
                    navController = navController,
                    items = items
                )
            }
        }
    ) {
        NavHost(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(paddingValues = it),
            navController = navController,
            startDestination = selectedTabDestination.graphRoute
        ) {
            with(bottomNavigationProvider) {
                summaryGraph.create(parentNavController, this@NavHost)
                settingsGraph.create(parentNavController, this@NavHost)
            }
        }
    }
}

@Composable
private fun NavController.currentDashboardDirectionAsState(initialTab: BottomNavigationDestination): State<BottomNavigationDestination> {
    val selectedItem = remember { mutableStateOf(initialTab) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            selectedItem.value = when (destination.parent?.route) {
                DashboardDestination.SummaryDestination.NavSummaryGraphDestination.graphRoute -> DashboardDestination.SummaryDestination.NavSummaryGraphDestination
                DashboardDestination.SettingsDestination.NavSettingsGraphDestination.graphRoute -> DashboardDestination.SettingsDestination.NavSettingsGraphDestination
                else -> DashboardDestination.SummaryDestination.NavSummaryGraphDestination
            }
        }

        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}
