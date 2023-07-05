package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultInputView(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = Color.Transparent,
    isError: Boolean = false,
    isPassword: Boolean = false,
    hintText: String = "",
    readOnly: Boolean = false,
    textAlign: TextAlign = TextAlign.Center,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
    trailingIcon: @Composable (() -> Unit)? = null,
    onViewClicked: () -> Unit = {}
) {
    TextField(
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = backgroundColor,
            textColor = if (isError) Color.Red else MaterialTheme.colorScheme.onPrimary,
            focusedBorderColor = if (isError) Color.Red else MaterialTheme.colorScheme.onPrimary,
            unfocusedBorderColor = if (isError) Color.Red else MaterialTheme.colorScheme.tertiary,
            cursorColor = MaterialTheme.colorScheme.onPrimary
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
                textAlign = TextAlign.Center
            )
        },
        textStyle = LocalTextStyle.current.copy(textAlign = textAlign),
        singleLine = true,
        readOnly = readOnly,
        keyboardOptions = keyboardOptions,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}
