package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
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
    val dimens = LocalDim.current
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .bottomBorder(1.dp, Color.LightGray)
            .fillMaxWidth()
    ) {

        ResultInputView(
            modifier = Modifier
                .focusRequester(focusRequesters[1])
                .width(dimens.first_list_item_size)
                .padding(start = dimens.space_8),
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
                .width(dimens.second_list_item_size)
                .padding(horizontal = dimens.space_8),
            value = newResultData.amount,
            onValueChange = {
                onAmountValueChanged(it)
            },
            isError = newResultData.isAmountError,
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
                .padding(bottom = dimens.space_16, end = dimens.space_8),
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