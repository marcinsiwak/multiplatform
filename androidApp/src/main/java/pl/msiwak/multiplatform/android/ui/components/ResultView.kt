package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder
import pl.msiwak.multiplatform.android.ui.theme.dimens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultView(
    details: List<String>,
    onResultLongClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .combinedClickable(
                enabled = true,
                onClick = {},
                onLongClick = {
                    onResultLongClick()
                })
            .bottomBorder(1.dp, MaterialTheme.colorScheme.tertiary)
    ) {

        details.forEach {
            Text(
                modifier = Modifier
                    .width(MaterialTheme.dimens.result_item_width)
                    .padding(
                        vertical = MaterialTheme.dimens.space_16,
                        horizontal = MaterialTheme.dimens.space_24
                        ),
                text = it,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
                )
        }
    }
}