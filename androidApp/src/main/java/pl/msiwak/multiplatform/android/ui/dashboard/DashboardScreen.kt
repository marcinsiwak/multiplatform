package pl.msiwak.multiplatform.android.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.components.BottomNavigation
import pl.msiwak.multiplatform.android.ui.settings.SettingsScreen
import pl.msiwak.multiplatform.android.ui.summary.SummaryScreen
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.commonResources.MR
import pl.msiwak.multiplatform.ui.dashboard.DashboardViewModel
import pl.msiwak.multiplatform.ui.navigator.DashboardNavigationDirections

@Composable
fun DashboardScreen() {
    val viewModel = koinViewModel<DashboardViewModel>()
    val viewState = viewModel.viewState.collectAsState()

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

    Scaffold(
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                if (viewState.value.isOfflineBannerVisible) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.tertiary),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(MaterialTheme.dimens.space_8),
                            text = "You are in a offline mode",
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onTertiary
                        )


                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier
                                .clickable { viewModel.onSignInUpClicked() }
                                .padding(horizontal = MaterialTheme.dimens.space_8),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier,
                                text = "Sign in/up",
                                style = MaterialTheme.typography.labelSmall,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onTertiary
                            )

                            Icon(
                                painter = painterResource(id = MR.images.ic_arrow_right.drawableResId),
                                tint = MaterialTheme.colorScheme.onTertiary,
                                contentDescription = null
                            )
                        }
                    }
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

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen()
}
