package pl.msiwak.multiplatform.android.ui.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.components.BottomNavigation
import pl.msiwak.multiplatform.android.ui.settings.SettingsScreen
import pl.msiwak.multiplatform.android.ui.summary.SummaryScreen
import pl.msiwak.multiplatform.commonResources.MR
import pl.msiwak.multiplatform.ui.dashboard.DashboardViewModel
import pl.msiwak.multiplatform.ui.navigator.DashboardNavigationDirections

@Composable
fun DashboardScreen() {
    val viewModel = koinViewModel<DashboardViewModel>()

    val items = listOf(
        DashboardNavigationDirections.Summary(
            MR.images.ic_workout.drawableResId,
            stringResource(MR.strings.summary.resourceId)
        ),
//        DashboardNavigationDirections.Account(R.drawable.ic_account, stringResource(MR.strings.account)),
        DashboardNavigationDirections.Settings(
            MR.images.ic_settings.drawableResId,
            stringResource(MR.strings.settings.resourceId)
        )
    )

    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigation(navController = navController, items = items) }) {
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

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}
