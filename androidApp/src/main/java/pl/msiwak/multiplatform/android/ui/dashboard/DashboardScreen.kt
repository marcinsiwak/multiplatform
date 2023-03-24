package pl.msiwak.multiplatform.android.ui.dashboard

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.library.MR
import org.koin.java.KoinJavaComponent
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.account.AccountScreen
import pl.msiwak.multiplatform.android.ui.components.BottomNavigation
import pl.msiwak.multiplatform.android.ui.settings.SettingsScreen
import pl.msiwak.multiplatform.android.ui.summary.SummaryScreen
import pl.msiwak.multiplatform.ui.dashboard.DashboardViewModel
import pl.msiwak.multiplatform.ui.navigator.DashboardNavigationDirections

@Composable
fun DashboardScreen() {
    val items = listOf(
        DashboardNavigationDirections.Summary(R.drawable.ic_workout, stringResource(id = MR.strings.summary.resourceId)),
        DashboardNavigationDirections.Account(R.drawable.ic_account, stringResource(id = MR.strings.account.resourceId)),
        DashboardNavigationDirections.Settings(R.drawable.ic_settings, stringResource(id = MR.strings.settings.resourceId))
    )

    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomNavigation(navController = navController, items = items) }) {
        NavHost(
            modifier = Modifier.padding(paddingValues = it),
            navController = navController,
            startDestination = items[0].route
        ) {
            composable(items[0].route) { SummaryScreen() }
            composable(items[1].route) { AccountScreen() }
            composable(items[2].route) { SettingsScreen() }
        }
    }
}