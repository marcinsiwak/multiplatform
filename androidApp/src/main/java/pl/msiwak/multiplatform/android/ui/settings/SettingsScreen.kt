package pl.msiwak.multiplatform.android.ui.settings

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.ui.settings.SettingsState
import pl.msiwak.multiplatform.ui.settings.SettingsViewModel

@Composable
fun SettingsScreen() {
    val viewModel = koinViewModel<SettingsViewModel>()
    val viewState = viewModel.viewState.collectAsState()

    SettingsScreenContent(
        viewState = viewState,
        onUnitClicked = viewModel::onUnitClicked,
        onLanguageClicked = viewModel::onLanguageClicked,
        onLogoutClicked = viewModel::onLogoutClicked
    )
}

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
                text = stringResource(SR.strings.settings.resourceId),
                fontSize = MaterialTheme.font.font_24,
                color = MaterialTheme.colorScheme.onPrimary
            )
            SettingsItem(
                modifier = Modifier
                    .padding(top = MaterialTheme.dimens.space_8)
                    .clickable {
                        onUnitClicked()
                    },
                text = stringResource(SR.strings.settings_unit.resourceId)
            )
            val isLanguageEnabled = false
            if (isLanguageEnabled) {
                SettingsItem(
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.space_8)
                        .clickable {
                            onLanguageClicked()
                        },
                    text = stringResource(SR.strings.settings_language.resourceId)
                )
            }
            if (viewState.value.isLogoutButtonVisible) {
                SettingsItem(
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.space_8)
                        .clickable {
                            onLogoutClicked()
                        },
                    text = stringResource(SR.strings.settings_logout.resourceId)
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

@Preview
@Composable
fun SettingsScreenPreview() {
    AppTheme {
        SettingsScreenContent(MutableStateFlow(SettingsState()).collectAsState())
    }
}
