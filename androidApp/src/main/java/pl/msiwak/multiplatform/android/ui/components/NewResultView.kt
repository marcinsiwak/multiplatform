package pl.msiwak.multiplatform.android.ui.components

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
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.FormattedResultData

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
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .bottomBorder(
                MaterialTheme.dimens.results_divider_height,
                MaterialTheme.colorScheme.tertiary
            )
            .padding(bottom = MaterialTheme.dimens.space_16)
            .fillMaxWidth()
    ) {
        ResultInputView(
            modifier = Modifier
                .focusRequester(focusRequesters[1])
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
                .focusRequester(focusRequesters[2])
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
                .focusRequester(focusRequesters[3])
                .width(IntrinsicSize.Max)
                .onFocusChanged {
                    if (it.hasFocus) {
                        onDateClicked()
                    }
                }
                .padding(horizontal = MaterialTheme.dimens.space_16),
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
