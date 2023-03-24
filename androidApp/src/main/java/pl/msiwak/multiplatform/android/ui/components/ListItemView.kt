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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder

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
            .bottomBorder(strokeWidth = 1.dp, color = Color.DarkGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
            text = name,
            fontSize = 20.sp,
            color = Color.LightGray
        )
        Icon(
            modifier = Modifier.padding(16.dp),
            tint = Color.LightGray,
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun ListItemViewPreview() {
    ListItemView(name = "AAAAAA")
}