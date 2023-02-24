package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NewResultView(
    onRemove: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            value = "aaa",
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White
            ),
            onValueChange = {

            }
        )

        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            value = "aa",
            onValueChange = {

            }
        )

        TextField(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            value = "aa",
            onValueChange = {

            }
        )
    }
}