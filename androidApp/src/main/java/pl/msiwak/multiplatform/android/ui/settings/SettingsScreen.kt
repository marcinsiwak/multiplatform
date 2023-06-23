package pl.msiwak.multiplatform.android.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import org.example.library.MR
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.ui.settings.SettingsViewModel

@Composable
fun SettingsScreen() {
    val viewModel = koinViewModel<SettingsViewModel>()
    val state = viewModel.viewState.collectAsState()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Text(
                modifier = Modifier.padding(
                    vertical = MaterialTheme.dimens.space_16,
                    horizontal = MaterialTheme.dimens.space_24
                ),
                text = stringResource(MR.strings.settings.resourceId),
                fontSize = MaterialTheme.font.font_24,
                color = Color.White
            )
            SettingsItem(
                modifier = Modifier
                    .padding(top = MaterialTheme.dimens.space_8)
                    .clickable {
                        viewModel.onUnitClicked()
                    },
                text = stringResource(MR.strings.settings_unit.resourceId)
            )
            val isLanguageEnabled = false
            if (isLanguageEnabled) {
                SettingsItem(
                    modifier = Modifier
                        .padding(top = MaterialTheme.dimens.space_8)
                        .clickable {
                            viewModel.onLanguageClicked()
                        },
                    text = stringResource(MR.strings.settings_language.resourceId)
                )
            }
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = MaterialTheme.dimens.space_16),
            text = state.value.versionName,
            color = Color.DarkGray
        )
    }
}