package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens

@Composable
fun TextWithDrawableView(
    modifier: Modifier,
    text: String,
    iconResId: ImageResource? = null,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    textAlign: TextAlign = TextAlign.Center
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = MaterialTheme.dimens.space_4,
                vertical = MaterialTheme.dimens.space_16
            ),
            text = text,
            color = color,
            textAlign = textAlign
        )
        val iconAlpha = if (iconResId != null) 1f else 0f
        Icon(
            modifier = Modifier
                .alpha(iconAlpha),
            painter = painterResource(iconResId ?: SR.images.ic_arrow_down),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}
