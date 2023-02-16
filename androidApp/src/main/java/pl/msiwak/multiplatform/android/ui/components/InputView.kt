package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputView(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = Color.Transparent,
    errorMessage: String? = null,
    isPassword: Boolean = false,
    hintText: String = "",
    readOnly: Boolean = false,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                textColor = Color.White,
                focusedBorderColor = colorResource(id = R.color.button_color),
                unfocusedBorderColor = Color.Gray
            ),
            shape = RoundedCornerShape(12.dp),
            value = value,
            onValueChange = { newText ->
                onValueChange(newText)
            },
            label = { Text(text = hintText) },
            singleLine = true,
            readOnly = readOnly,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
    val isErrorVisible = if (errorMessage.isNullOrEmpty()) 0f else 1f
    Text(
        modifier = Modifier
            .alpha(isErrorVisible)
            .padding(bottom = 8.dp),
        text = errorMessage ?: "",
        color = Color.Red
    )

}

@Preview
@Composable
fun InputViewPreview() {
    InputView(modifier = Modifier, value = "", onValueChange = {})
}