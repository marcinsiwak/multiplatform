package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.FormattedResultData

@Composable
fun NewResultView(
    modifier: Modifier = Modifier,
    focusRequesters: List<FocusRequester>,
    newResultData: FormattedResultData,
    exerciseType: ExerciseType,
    onResultValueChanged: (String) -> Unit,
    onAmountValueChanged: (String) -> Unit,
    onDateValueChanged: (String) -> Unit,
    onDateClicked: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .bottomBorder(1.dp, MaterialTheme.colorScheme.tertiary)
            .fillMaxWidth()
    ) {

        ResultInputView(
            modifier = Modifier
                .focusRequester(focusRequesters[1])
                .width(MaterialTheme.dimens.first_list_item_size)
                .padding(start = MaterialTheme.dimens.space_8),
            value = newResultData.result,
            onValueChange = {
                onResultValueChanged(it)
            },
            isError = newResultData.isResultError,
            hintText = "100"
        )

        ResultInputView(
            modifier = Modifier
                .focusRequester(focusRequesters[2])
                .width(MaterialTheme.dimens.second_list_item_size)
                .padding(horizontal = MaterialTheme.dimens.space_8),
            value = newResultData.amount,
            onValueChange = {
                onAmountValueChanged(it)
            },
            isError = newResultData.isAmountError,
            keyboardOptions = if (exerciseType == ExerciseType.GYM) {
                KeyboardOptions(keyboardType = KeyboardType.Decimal)
            } else {
                KeyboardOptions(keyboardType = KeyboardType.Text)
            },
            hintText = if (exerciseType == ExerciseType.GYM) "10" else "00:00.000"
        )
        ResultInputView(
            modifier = Modifier
                .focusRequester(focusRequesters[3])
                .onFocusChanged {
                    if (it.hasFocus) {
                        onDateClicked()
                        focusManager.clearFocus()
                    }
                }
                .padding(
                    bottom = MaterialTheme.dimens.space_16,
                    end = MaterialTheme.dimens.space_8
                ),
            value = newResultData.date,
            onValueChange = {
                onDateValueChanged(it)
            },
            isError = newResultData.isDateError,
            hintText = "01.01.2023",
            onViewClicked = {
                onDateClicked()
            }
        )
    }
}