package pl.msiwak.multiplatform.android.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.library.MR
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.utils.getString
import pl.msiwak.multiplatform.ui.settings.SettingsViewModel

@Composable
fun SettingsScreen() {
    val viewModel = koinViewModel<SettingsViewModel>()
    val dimens = LocalDim.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Text(
            modifier = Modifier.padding(vertical = dimens.space_16, horizontal = dimens.space_24),
            text = getString(context, MR.strings.settings),
            fontSize = dimens.font_24,
            color = Color.White
        )
        val isLanguageEnabled = false
        if (isLanguageEnabled) {
            SettingsItem(
                modifier = Modifier
                    .padding(top = dimens.space_8)
                    .clickable {
                        viewModel.onLanguageClicked()
                    },
                text = getString(context, MR.strings.settings_language)
            )
        }
    }
}