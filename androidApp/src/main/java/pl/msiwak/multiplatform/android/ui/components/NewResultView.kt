package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.data.common.FormattedResultData

@Composable
fun NewResultView(
    newResultData: FormattedResultData,
    onResultValueChanged: (String) -> Unit,
    onAmountValueChanged: (String) -> Unit,
    onDateValueChanged: (String) -> Unit,
    onDateClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
    ) {

        ResultInputView(
            modifier = Modifier
                .width(132.dp)
                .padding(horizontal = 16.dp),
            value = newResultData.result,
            onValueChange = {
                onResultValueChanged(it)
            }
        )

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        ResultInputView(
            modifier = Modifier
                .width(132.dp)
                .padding(horizontal = 16.dp),
            value = newResultData.amount,
            onValueChange = {
                onAmountValueChanged(it)
            }
        )

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        ResultInputView(
            modifier = Modifier
                .width(132.dp)
                .padding(horizontal = 16.dp),
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