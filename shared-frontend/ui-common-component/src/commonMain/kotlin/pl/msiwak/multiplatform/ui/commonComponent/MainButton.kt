package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import pl.msiwak.multiplatform.commonResources.theme.dimens

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    leadingIcon: DrawableResource? = null,
    text: String
) {
    Button(
        modifier = modifier.height(MaterialTheme.dimens.default_button_height),
        enabled = enabled,
        onClick = onClick,
        border = BorderStroke(
            MaterialTheme.dimens.border_width,
            MaterialTheme.colorScheme.onPrimary
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Box(
            modifier = Modifier.height(IntrinsicSize.Min).fillMaxWidth()
        ) {
            leadingIcon?.let {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(MaterialTheme.dimens.google_icon_size)
                        .padding(start = MaterialTheme.dimens.space_16),
                    painter = painterResource(it),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = text
            )
        }
    }
}
