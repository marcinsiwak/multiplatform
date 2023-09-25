package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import pl.msiwak.multiplatform.android.ui.theme.dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputView(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = Color.Transparent,
    errorMessage: String? = null,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    hintText: String = "",
    readOnly: Boolean = false,
    errorsEnabled: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = if (errorMessage != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimary,
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                disabledContainerColor = backgroundColor,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedBorderColor = if (errorMessage != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = if (errorMessage != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary,
                focusedLabelColor = if (errorMessage != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimary,
                unfocusedLabelColor = if (errorMessage != null) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary,
            ),
            trailingIcon = trailingIcon,
            shape = RoundedCornerShape(MaterialTheme.dimens.space_12),
            value = value,
            onValueChange = { newText ->
                onValueChange(newText)
            },
            label = {
                Text(
                    text = hintText,
                    maxLines = 1,
                )
            },
            singleLine = true,
            readOnly = readOnly,
            visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
    if (errorsEnabled) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.dimens.space_8),
            text = errorMessage ?: "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Preview
@Composable
fun InputViewPreview() {
    InputView(modifier = Modifier, value = "", onValueChange = {})
}