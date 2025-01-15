package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.msiwak.multiplatform.commonResources.theme.dimens

@Composable
fun SecondaryButton(modifier: Modifier = Modifier, enabled: Boolean = true, onClick: () -> Unit, text: String) {
    Button(
        enabled = enabled,
        modifier = modifier.height(MaterialTheme.dimens.default_button_height),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {
        Text(text = text)
    }
}
