package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.resources.compose.painterResource
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.ui.commonComponent.extension.bottomBorder

@Suppress("MagicNumber")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListItemView(name: String, onItemClick: () -> Unit = {}, onLongClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                enabled = true,
                onClick = { onItemClick() },
                onLongClick = { onLongClick() },
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.LightGray
                )
            )
            .bottomBorder(
                strokeWidth = MaterialTheme.dimens.space_1,
                color = MaterialTheme.colorScheme.secondary
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = MaterialTheme.dimens.space_16,
                    top = MaterialTheme.dimens.space_8,
                    bottom = MaterialTheme.dimens.space_8
                ),
            text = name,
            fontSize = MaterialTheme.font.font_20,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Icon(
            modifier = Modifier
                .weight(0.2f)
                .padding(MaterialTheme.dimens.space_16),
            tint = MaterialTheme.colorScheme.tertiary,
            painter = painterResource(SR.images.ic_arrow_right),
            contentDescription = null
        )
    }
}

// @Preview
// @Composable
// fun ListItemViewPreview() {
//     AppTheme {
//         ListItemView(name = "AAAAAA")
//     }
// }
