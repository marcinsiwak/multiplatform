package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.theme.LocalDim

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultInputView(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = Color.Transparent,
    errorMessage: String? = null,
    isError: Boolean = false,
    isPassword: Boolean = false,
    hintText: String = "",
    readOnly: Boolean = false,
    textAlign: TextAlign = TextAlign.Center,
    errorsEnabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val dimens = LocalDim.current

    Column(
        modifier = modifier
    ) {
        TextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = backgroundColor,
                textColor = if (isError) Color.Red else colorResource(id = R.color.white),
                focusedBorderColor = if (isError) Color.Red else colorResource(id = R.color.white),
                unfocusedBorderColor = if (isError) Color.Red else Color.Gray,
                cursorColor = colorResource(id = R.color.white)
            ),
            trailingIcon = trailingIcon,
            shape = RoundedCornerShape(dimens.space_12),
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
            textStyle = LocalTextStyle.current.copy(textAlign = textAlign),
            singleLine = true,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
    val isErrorVisible = if (errorMessage.isNullOrEmpty()) 0f else 1f
    if (errorsEnabled) {
        Text(
            modifier = Modifier
                .alpha(isErrorVisible)
                .padding(bottom = dimens.space_8),
            text = errorMessage ?: "",
            color = Color.Red
        )
    }
}
