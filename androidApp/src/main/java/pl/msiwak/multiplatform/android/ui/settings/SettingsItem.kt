package pl.msiwak.multiplatform.android.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font

@Composable
fun SettingsItem(modifier: Modifier = Modifier, text: String) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(
                vertical = MaterialTheme.dimens.space_16,
                horizontal = MaterialTheme.dimens.space_24
            ),
            text = text,
            fontSize = MaterialTheme.font.font_16,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Divider(
            modifier = Modifier.padding(horizontal = MaterialTheme.dimens.space_16),
            thickness = MaterialTheme.dimens.space_1,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}