package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.data.common.FormattedResultData

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewResultView(
    modifier: Modifier = Modifier,
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
                .width(dimens.first_list_item_size)
                .padding(horizontal = dimens.space_16),
            value = newResultData.result,
            onValueChange = {
                onResultValueChanged(it)
            }
        )

        ResultInputView(
            modifier = Modifier
                .width(dimens.second_list_item_size)
                .padding(horizontal = dimens.space_16),
            value = newResultData.amount,
            onValueChange = {
                onAmountValueChanged(it)
            }
        )
        ResultInputView(
            modifier = Modifier
                .padding(start = dimens.space_16, bottom = dimens.space_16, end = dimens.space_4),
            value = newResultData.date,
            onValueChange = {
                onDateValueChanged(it)
            },
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