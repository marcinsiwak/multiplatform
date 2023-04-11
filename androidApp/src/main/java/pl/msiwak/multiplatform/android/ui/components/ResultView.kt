package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.ui.theme.LocalDim

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultView(
    result: String,
    amount: String,
    date: String,
    onResultLongClick: () -> Unit = {},
) {
    val dimens = LocalDim.current

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .combinedClickable(
                enabled = true,
                onClick = {},
                onLongClick = {
                    onResultLongClick()
                }),
    ) {
        Text(
            modifier = Modifier
                .width(dimens.space_132)
                .padding(vertical = dimens.space_16),
            text = result,
            color = Color.White,
            textAlign = TextAlign.Center,

            )
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxHeight()
                .width(dimens.space_1)
        )
        Text(
            modifier = Modifier
                .width(dimens.space_132)
                .padding(vertical = dimens.space_16),
            text = amount,
            color = Color.White,
            textAlign = TextAlign.Center,

            )
        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxHeight()
                .width(dimens.space_1)
        )
        Text(
            modifier = Modifier
                .width(dimens.space_132)
                .padding(vertical = dimens.space_16),
            text = date,
            color = Color.White,
            textAlign = TextAlign.Center,

            )
    }
}