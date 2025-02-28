package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import pl.msiwak.multiplatform.commonResources.theme.color

@Composable
fun ResultInputView(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = Color.Transparent,
    isError: Boolean = false,
    hintText: String = "",
    readOnly: Boolean = false,
    textAlign: TextAlign = TextAlign.Center,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = if (isError) Color.Red else MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = if (isError) Color.Red else MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor,
            disabledContainerColor = backgroundColor,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            focusedBorderColor = if (isError) Color.Red else MaterialTheme.colorScheme.onPrimary,
            unfocusedBorderColor = if (isError) Color.Red else MaterialTheme.colorScheme.tertiary
        ),
        value = value,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        placeholder = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = hintText,
                maxLines = 1,
                color = MaterialTheme.color.HintColor,
                textAlign = TextAlign.Center
            )
        },
        textStyle = LocalTextStyle.current.copy(textAlign = textAlign),
        singleLine = true,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}
