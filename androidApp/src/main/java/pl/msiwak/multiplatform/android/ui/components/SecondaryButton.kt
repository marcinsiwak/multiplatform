package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pl.msiwak.multiplatform.android.ui.theme.dimens

@Composable
fun SecondaryButton(modifier: Modifier, onClick: () -> Unit, text: String) {
    Button(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(MaterialTheme.dimens.border_width, MaterialTheme.colorScheme.onPrimary),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Text(text = text)
    }
}
