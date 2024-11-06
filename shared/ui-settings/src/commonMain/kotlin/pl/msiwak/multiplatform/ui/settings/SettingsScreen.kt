package pl.msiwak.multiplatform.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.settings
import athletetrack.shared.commonresources.generated.resources.settings_language
import athletetrack.shared.commonresources.generated.resources.settings_logout
import athletetrack.shared.commonresources.generated.resources.settings_unit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.navigator.destination.NavDestination.LanguageDestination
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(true) {
        viewModel.viewEvent.collectLatest {
            when (it) {
                SettingsEvent.Logout -> navController.navigate(NavDestination.WelcomeDestination.NavWelcomeScreen.route)
            }
        }
    }

    SettingsScreenContent(
        viewState = viewState,
        onUiAction = {
            when (it) {
                SettingsUiAction.OnLanguageClicked -> navController.navigate(LanguageDestination.NavLanguageScreen.route)
                SettingsUiAction.OnUnitClicked -> navController.navigate(NavDestination.UnitDestination.NavUnitScreen.route)
                else -> viewModel.onUiAction(it)
            }
        }
    )
}

@Composable
fun SettingsScreenContent(
    viewState: State<SettingsState>,
    onUiAction: (SettingsUiAction) -> Unit
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.tertiary)
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
                        onUiAction(SettingsUiAction.OnUnitClicked)
                    },
                text = stringResource(Res.string.settings_unit)
            )
            val isLanguageEnabled = false
            if (isLanguageEnabled) {
                SettingsItem(
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.space_8)
                        .clickable {
                            onUiAction(SettingsUiAction.OnLanguageClicked)
                        },
                    text = stringResource(Res.string.settings_language)
                )
            }
            if (viewState.value.isLogoutButtonVisible) {
                SettingsItem(
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.space_8)
                        .clickable {
                            onUiAction(SettingsUiAction.OnLogoutClicked)
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

@DarkLightPreview
@Composable
fun SettingsScreenPreview() {
    AppTheme {
        SettingsScreenContent(MutableStateFlow(SettingsState()).collectAsState()) {}
    }
}
