package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultView(
    result: String,
    amount: String,
    date: String,
    onResultLongClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .combinedClickable(
                enabled = true,
                onClick = {},
                onLongClick = {
                    onResultLongClick()
                }),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp), text = result, color = Color.White
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp), text = amount, color = Color.White
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp), text = date, color = Color.White
        )
    }
}