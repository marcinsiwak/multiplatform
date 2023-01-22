package pl.msiwak.multiplatform.android.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputView(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = Color.Transparent
) {
    OutlinedTextField(
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = backgroundColor,
            focusedBorderColor = colorResource(id = R.color.button_color),
            unfocusedBorderColor = Color.Gray
        ),
        shape = RoundedCornerShape(12.dp),
        value = value,
        onValueChange = { newText ->
            onValueChange(newText)
        }
    )
}

@Preview
@Composable
fun InputViewPreview() {
    InputView(modifier = Modifier, value = "", onValueChange = {})
}