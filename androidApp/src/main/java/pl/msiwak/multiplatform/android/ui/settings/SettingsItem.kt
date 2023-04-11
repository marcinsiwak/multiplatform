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

@Composable
fun SettingsItem(modifier: Modifier = Modifier, text: String) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
            text = text,
            fontSize = 16.sp,
            color = Color.White,
        )
        Divider(thickness = 1.dp, color = Color.White)
    }
}