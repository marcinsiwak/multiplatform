package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.R

@Composable
fun ResultView(
    modifier: Modifier = Modifier,
    result: String,
    date: String,
    onRemove: () -> Unit = {},
) {
    Row(modifier = modifier) {
        Text(modifier = Modifier.weight(1f).padding(16.dp), text = result, color = Color.White)
        Text(modifier = Modifier.weight(1f).padding(16.dp), text = date, color = Color.White)
        Icon(
            modifier = Modifier
                .weight(0.3f)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .clickable { onRemove() },
            tint = Color.White,
            painter = painterResource(id = R.drawable.ic_remove),
            contentDescription = "Add result"
        )
    }
}