package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.FormattedResultData
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.extension.bottomBorder

private const val RESULT_ITEM_POSITION = 1
private const val AMOUNT_ITEM_POSITION = 2
private const val DATE_ITEM_POSITION = 3

@Composable
fun NewResultView(
    modifier: Modifier = Modifier,
    focusRequesters: List<FocusRequester>,
    newResultData: FormattedResultData,
    exerciseType: ExerciseType,
    onResultValueChanged: (String) -> Unit,
    onAmountValueChanged: (String) -> Unit,
    onDateValueChanged: (String) -> Unit,
    onDateClicked: () -> Unit,
    onAmountClicked: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .bottomBorder(
                MaterialTheme.dimens.results_divider_height,
                MaterialTheme.colorScheme.tertiary
            )
            .padding(bottom = MaterialTheme.dimens.space_16)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ResultInputView(
            modifier = Modifier
                .focusRequester(focusRequesters[RESULT_ITEM_POSITION])
                .width(MaterialTheme.dimens.result_item_input_width)
                .padding(horizontal = MaterialTheme.dimens.space_16),
            value = newResultData.result,
            onValueChange = {
                onResultValueChanged(it)
            },
            isError = newResultData.isResultError,
            hintText = "100"
        )

        ResultInputView(
            modifier = Modifier
                .focusRequester(focusRequesters[AMOUNT_ITEM_POSITION])
                .width(MaterialTheme.dimens.result_item_input_width)
                .onFocusChanged {
                    if (exerciseType == ExerciseType.RUNNING && it.hasFocus) {
                        onAmountClicked()
                    }
                },
            value = newResultData.amount,
            onValueChange = {
                onAmountValueChanged(it)
            },
            isError = newResultData.isAmountError,
            hintText = if (exerciseType == ExerciseType.GYM) "10" else "00:00:00.000"
        )
        ResultInputView(
            modifier = Modifier
                .focusRequester(focusRequesters[DATE_ITEM_POSITION])
                .width(IntrinsicSize.Max)
                .onFocusChanged {
                    if (it.hasFocus) {
                        onDateClicked()
                        focusManager.clearFocus()
                    }
                }
                .padding(horizontal = MaterialTheme.dimens.space_16),
            value = newResultData.date,
            onValueChange = {
                onDateValueChanged(it)
            },
            isError = newResultData.isDateError,
            hintText = "01.01.2023"
        )
    }
}
