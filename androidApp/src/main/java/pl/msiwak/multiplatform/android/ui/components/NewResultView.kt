package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.data.common.FormattedResultData

@Composable
fun NewResultView(
    modifier: Modifier = Modifier,
    focusRequesters: List<FocusRequester>,
    newResultData: FormattedResultData,
    onResultValueChanged: (String) -> Unit,
    onAmountValueChanged: (String) -> Unit,
    onDateValueChanged: (String) -> Unit,
    onDateClicked: () -> Unit
) {
    val dimens = LocalDim.current

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
                .padding(horizontal = dimens.space_16),
            value = newResultData.result,
            isError = newResultData.isResultError,
            onValueChange = {
                onResultValueChanged(it)
            }
        )

        ResultInputView(
            modifier = Modifier
                .focusRequester(focusRequesters[2])
                .width(dimens.second_list_item_size)
                .padding(horizontal = dimens.space_16),
            value = newResultData.amount,
            isError = newResultData.isAmountError,
            onValueChange = {
                onAmountValueChanged(it)
            }
        )
        ResultInputView(
            modifier = Modifier
                .focusRequester(focusRequesters[3])
                .padding(start = dimens.space_16, bottom = dimens.space_16, end = dimens.space_4),
            value = newResultData.date,
            onValueChange = {
                onDateValueChanged(it)
            },
            isError = newResultData.isDateError,
            textAlign = TextAlign.End,
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        onDateClicked()
                    },
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = null
                )
            }
        )
    }
}