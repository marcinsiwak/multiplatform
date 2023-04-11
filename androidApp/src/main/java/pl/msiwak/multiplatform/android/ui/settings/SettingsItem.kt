package pl.msiwak.multiplatform.android.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.library.MR
import pl.msiwak.multiplatform.android.ui.theme.LocalDim

@Composable
fun SettingsItem(modifier: Modifier = Modifier, text: String) {
    val dimens = LocalDim.current

    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(vertical = dimens.space_16, horizontal = dimens.space_24),
            text = text,
            fontSize = dimens.font_16,
            color = Color.White,
        )
        Divider(thickness = dimens.space_1, color = Color.White)
    }
}