package pl.msiwak.multiplatform.android.ui.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.library.MR
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.components.BottomNavigation
import pl.msiwak.multiplatform.android.ui.settings.SettingsScreen
import pl.msiwak.multiplatform.android.ui.summary.SummaryScreen
import pl.msiwak.multiplatform.ui.dashboard.DashboardViewModel
import pl.msiwak.multiplatform.ui.navigator.DashboardNavigationDirections

@Composable
fun DashboardScreen() {
    val viewModel = koinViewModel<DashboardViewModel>()

    val context = LocalContext.current

    val items = listOf(
        DashboardNavigationDirections.Summary(
            R.drawable.ic_workout,
            stringResource(MR.strings.summary.resourceId)
        ),
//        DashboardNavigationDirections.Account(R.drawable.ic_account, stringResource(MR.strings.account)),
        DashboardNavigationDirections.Settings(
            R.drawable.ic_settings,
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