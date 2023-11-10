package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.commonResources.MR

@Composable
fun RunningTimeInputDialog(
    onConfirm: (String, String, String, String) -> Unit,
    onDismiss: () -> Unit
) {
    val hours = remember { mutableStateOf("") }
    val minutes = remember { mutableStateOf("") }
    val seconds = remember { mutableStateOf("") }
    val milliseconds = remember { mutableStateOf("") }

    val isMinutesError = remember {
        mutableStateOf(false)
    }
    val isSecondsError = remember {
        mutableStateOf(false)
    }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .background(
                    colorResource(MR.colors.gray.resourceId),
                    RoundedCornerShape(MaterialTheme.dimens.dialog_corners)
                )
                .padding(MaterialTheme.dimens.space_16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.dimens.space_8),
                text = stringResource(id = MR.strings.running_time_input_insert_result.resourceId),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            Row(
                modifier = Modifier.padding(vertical = MaterialTheme.dimens.space_16)
            ) {
                RunningTimeInputView(
                    value = hours.value,
                    onValueChange = {
                        if (it.length > 2) return@RunningTimeInputView

                        hours.value = it.filter { word -> word.isDigit() }
                    },
                    placeholder = {
                        Text("0")
                    },
                    label = {
                        Text(stringResource(id = MR.strings.hours.resourceId))
                    }
                )

                RunningTimeInputView(
                    isError = isMinutesError.value,
                    value = minutes.value,
                    onValueChange = {
                        if (it.length > 2) return@RunningTimeInputView

                        isMinutesError.value = false
                        minutes.value = it.filter { word -> word.isDigit() }
                    },
                    placeholder = {
                        Text("0")
                    },
                    label = {
                        Text(stringResource(id = MR.strings.minutes.resourceId))
                    }
                )
            }
            Row(
                modifier = Modifier.padding(bottom = MaterialTheme.dimens.space_16)
            ) {
                RunningTimeInputView(
                    isError = isSecondsError.value,
                    value = seconds.value,
                    onValueChange = {
                        if (it.length > 2) return@RunningTimeInputView

                        isSecondsError.value = false
                        seconds.value = it.filter { word -> word.isDigit() }
                    },
                    placeholder = {
                        Text("0")
                    },
                    label = {
                        Text(stringResource(id = MR.strings.seconds.resourceId))
                    }
                )
                RunningTimeInputView(
                    value = milliseconds.value,
                    onValueChange = {
                        if (it.length > 3) return@RunningTimeInputView

                        milliseconds.value = it.filter { word -> word.isDigit() }
                    },
                    placeholder = {
                        Text("0")
                    },
                    label = {
                        Text(stringResource(id = MR.strings.milliseconds.resourceId))
                    }
                )
            }
            SecondaryButton(
                modifier = Modifier.padding(vertical = MaterialTheme.dimens.space_8),
                text = stringResource(id = MR.strings.confirm.resourceId),
                onClick = {
                    if (hours.value.isEmpty()) {
                        hours.value = "0"
                    }
                    if (minutes.value.isEmpty()) {
                        minutes.value = "0"
                    }
                    if (seconds.value.isEmpty()) {
                        seconds.value = "0"
                    }
                    if (milliseconds.value.isEmpty()) {
                        milliseconds.value = "0"
                    }
                    when {
                        minutes.value.toInt() > 60 -> isMinutesError.value = true
                        seconds.value.toInt() > 60 -> isSecondsError.value = true
                        else -> onConfirm(
                            hours.value,
                            minutes.value,
                            seconds.value,
                            milliseconds.value
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.RunningTimeInputView(
    value: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    placeholder: @Composable () -> Unit,
    label: @Composable () -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = MaterialTheme.dimens.space_16),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        isError = isError,
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            errorBorderColor = MaterialTheme.colorScheme.error,
            errorLabelColor = MaterialTheme.colorScheme.error,
            errorContainerColor = MaterialTheme.colorScheme.error,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onPrimary,
            focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedLabelColor = MaterialTheme.colorScheme.tertiary
        ),
        placeholder = placeholder,
        label = label
    )
}
