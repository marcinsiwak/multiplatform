@file:OptIn(ExperimentalResourceApi::class)

package pl.msiwak.multiplatform.android.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.ic_settings
import athletetrack.shared.commonresources.generated.resources.ic_workout
import athletetrack.shared.commonresources.generated.resources.settings
import athletetrack.shared.commonresources.generated.resources.summary
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.components.BottomNavigation
import pl.msiwak.multiplatform.android.ui.utils.DarkLightPreview
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.navigator.DashboardNavigationDirections
import pl.msiwak.multiplatform.ui.commonComponent.OfflineBanner
import pl.msiwak.multiplatform.ui.dashboard.DashboardState
import pl.msiwak.multiplatform.ui.dashboard.DashboardViewModel
import pl.msiwak.multiplatform.ui.settings.SettingsScreen
import pl.msiwak.multiplatform.ui.summary.SummaryScreen

@Composable
fun DashboardScreen() {
    val viewModel = koinViewModel<DashboardViewModel>()
    val viewState = viewModel.viewState.collectAsState()

    val navController = rememberNavController()

    val items = listOf(
        DashboardNavigationDirections.Summary(
            Res.drawable.ic_workout,
            Res.string.summary
        ),
//        DashboardNavigationDirections.Account(R.drawable.ic_account, stringResource(Res.string.account)),
        DashboardNavigationDirections.Settings(
            Res.drawable.ic_settings,
            Res.string.settings
        )
    )

    DashboardScreenContent(
        items = items,
        viewState = viewState,
        onSignInUpClicked = viewModel::onSignInUpClicked,
        navController = navController
    )
}

@Composable
fun DashboardScreenContent(
    items: List<DashboardNavigationDirections>,
    viewState: State<DashboardState>,
    onSignInUpClicked: () -> Unit = {},
    navController: NavHostController
) {
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
            startDestination = items[0].route
        ) {
            composable(items[0].route) { SummaryScreen() }
//            composable(items[1].route) { AccountScreen() }
            composable(items[1].route) { SettingsScreen() }
        }
    }
}

@DarkLightPreview
@Composable
fun DashboardScreenPreview() {
    AppTheme {
        DashboardScreenContent(
            items = listOf(),
            viewState = MutableStateFlow(DashboardState()).collectAsState(),
            navController = NavHostController(LocalContext.current)
        )
    }
}
