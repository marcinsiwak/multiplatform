package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.theme.dimens

@Composable
fun MainButton(modifier: Modifier, onClick: () -> Unit, text: String) {
    Button(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(
            MaterialTheme.dimens.border_width,
            MaterialTheme.colorScheme.onPrimary
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    ) {
        Row {
//            Image(painter = painterResource(id = com.google.android.gms.auth.api.R.drawable.common_google_signin_btn_text_light), contentDescription = null)
            Text(text = text)
        }
    }
}
