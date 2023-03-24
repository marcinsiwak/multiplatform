package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        InputView(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            value = newResultData.result,
            onValueChange = {
                onResultValueChanged(it)
            }
        )

        InputView(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            value = newResultData.amount,
            onValueChange = {
                onAmountValueChanged(it)
            }
        )
        InputView(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
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