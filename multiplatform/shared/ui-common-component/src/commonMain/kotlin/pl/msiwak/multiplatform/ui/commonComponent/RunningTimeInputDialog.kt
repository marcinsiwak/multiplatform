package pl.msiwak.multiplatform.ui.commonComponent

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.confirm
import athletetrack.shared.commonresources.generated.resources.hours
import athletetrack.shared.commonresources.generated.resources.milliseconds
import athletetrack.shared.commonresources.generated.resources.minutes
import athletetrack.shared.commonresources.generated.resources.running_time_input_insert_result
import athletetrack.shared.commonresources.generated.resources.seconds
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.commonResources.theme.dimens

private const val MILLISECONDS_DIGITS_AMOUNT = 3
private const val MINUTE_IN_SECONDS = 60
private const val HOUR_IN_MINUTES = 60

@OptIn(ExperimentalResourceApi::class)
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
                    Color.DarkGray,
                    RoundedCornerShape(MaterialTheme.dimens.dialog_corners)
                )
                .padding(MaterialTheme.dimens.space_16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.dimens.space_8),
                text = stringResource(Res.string.running_time_input_insert_result),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
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
                        Text(stringResource(Res.string.hours))
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
                        Text(stringResource(Res.string.minutes))
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
                        Text(stringResource(Res.string.seconds))
                    }
                )
                RunningTimeInputView(
                    value = milliseconds.value,
                    onValueChange = {
                        if (it.length > MILLISECONDS_DIGITS_AMOUNT) return@RunningTimeInputView

                        milliseconds.value = it.filter { word -> word.isDigit() }
                    },
                    placeholder = {
                        Text("0")
                    },
                    label = {
                        Text(stringResource(Res.string.milliseconds))
                    }
                )
            }
            SecondaryButton(
                modifier = Modifier.padding(vertical = MaterialTheme.dimens.space_8),
                text = stringResource(Res.string.confirm),
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
                        minutes.value.toInt() > HOUR_IN_MINUTES -> isMinutesError.value = true
                        seconds.value.toInt() > MINUTE_IN_SECONDS -> isSecondsError.value = true
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
