package pl.msiwak.multiplatform.android.ui.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder
import pl.msiwak.multiplatform.android.ui.theme.color
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.commonResources.MR

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
                ),
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
            painter = painterResource(id =  MR.images.ic_arrow_right.drawableResId),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun ListItemViewPreview() {
    ListItemView(name = "AAAAAA")
}