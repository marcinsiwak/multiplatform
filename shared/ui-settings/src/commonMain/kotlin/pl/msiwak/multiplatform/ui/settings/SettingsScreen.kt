@file:OptIn(ExperimentalResourceApi::class)

package pl.msiwak.multiplatform.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.settings
import athletetrack.shared.commonresources.generated.resources.settings_language
import athletetrack.shared.commonresources.generated.resources.settings_logout
import athletetrack.shared.commonresources.generated.resources.settings_unit
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import androidx.navigation.NavController
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.navigator.destination.NavDestination.LanguageDestination

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    SettingsScreenContent(
        viewState = viewState,
        onUnitClicked = {
            navController.navigate(NavDestination.UnitDestination.NavUnitScreen.route)
        },
        onLanguageClicked = {
            navController.navigate(LanguageDestination.NavLanguageScreen.route)
        },
        onLogoutClicked = viewModel::onLogoutClicked
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SettingsScreenContent(
    viewState: State<SettingsState>,
    onUnitClicked: () -> Unit = {},
    onLanguageClicked: () -> Unit = {},
    onLogoutClicked: () -> Unit = {}
) {
    Box {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(
                    vertical = MaterialTheme.dimens.space_16,
                    horizontal = MaterialTheme.dimens.space_24
                ),
                text = stringResource(Res.string.settings),
                fontSize = MaterialTheme.font.font_24,
                color = MaterialTheme.colorScheme.onPrimary
            )
            SettingsItem(
                modifier = Modifier
                    .padding(top = MaterialTheme.dimens.space_8)
                    .clickable {
                        onUnitClicked()
                    },
                text = stringResource(Res.string.settings_unit)
            )
            val isLanguageEnabled = false
            if (isLanguageEnabled) {
                SettingsItem(
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.space_8)
                        .clickable {
                            onLanguageClicked()
                        },
                    text = stringResource(Res.string.settings_language)
                )
            }
            if (viewState.value.isLogoutButtonVisible) {
                SettingsItem(
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.space_8)
                        .clickable {
                            onLogoutClicked()
                        },
                    text = stringResource(Res.string.settings_logout)
                )
            }
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = MaterialTheme.dimens.space_16),
            text = viewState.value.versionName,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

// @Preview
// @Composable
// fun SettingsScreenPreview() {
//     AppTheme {
//         SettingsScreenContent(MutableStateFlow(SettingsState()).collectAsState())
//     }
// }
