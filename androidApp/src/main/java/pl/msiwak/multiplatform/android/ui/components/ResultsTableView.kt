package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.library.MR
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.utils.getString
import pl.msiwak.multiplatform.data.common.FormattedResultData

@Composable
fun ResultsTableView(
    results: List<FormattedResultData>,
    isNewResultEnabled: Boolean,
    newResultData: FormattedResultData = FormattedResultData(),
    onAddNewResultClicked: () -> Unit = {},
    onResultValueChanged: (String) -> Unit = {},
    onAmountValueChanged: (String) -> Unit = {},
    onDateValueChanged: (String) -> Unit = {},
    onDateClicked: () -> Unit = {},
    onResultLongClick: (Int) -> Unit = {}
) {
    val dimens = LocalDim.current

    Column {
        // todo results type from database
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(vertical = dimens.space_16)
                .bottomBorder(3.dp, Color.LightGray)
        ) {
            Text(modifier = Modifier.width(dimens.space_80).padding(horizontal = dimens.space_8, vertical = dimens.space_16), text = "Weight", color = Color.White, textAlign = TextAlign.Center)
            Text(modifier = Modifier.width(dimens.space_80).padding(horizontal = dimens.space_8, vertical = dimens.space_16), text = "Reps", color = Color.White, textAlign = TextAlign.Center)
            Text(modifier = Modifier.fillMaxWidth().padding(horizontal = dimens.space_8, vertical = dimens.space_16), text = "Date", color = Color.White, textAlign = TextAlign.Center)
        }
        LazyColumn {
            if (results.isEmpty() && !isNewResultEnabled) {
                item {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimens.space_32)
                            .clickable {
                                onAddNewResultClicked()
                            },
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        text = "Add first result"
                    )
                }
            }
            itemsIndexed(results) { pos, item ->
                ResultView(
                    result = item.result,
                    amount = item.amount,
                    date = item.date,
                    onResultLongClick = {
                        onResultLongClick(pos)
                    })
            }


            if (results.isNotEmpty() && !isNewResultEnabled) {
                item {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.white_transparent),
                            contentColor = Color.Black
                        ),
                        contentPadding = PaddingValues(0.dp),
                        shape = RectangleShape,
                        onClick = { onAddNewResultClicked() }
                    ) {
                        Text(
                            text = getString(LocalContext.current, MR.strings.add_new_result),
                            fontSize = dimens.font_16
                        )
                    }
                }
            }

            if (isNewResultEnabled) {
                item {
                    NewResultView(
                        newResultData = newResultData,
                        onResultValueChanged = {
                            onResultValueChanged(it)
                        }, onAmountValueChanged = {
                            onAmountValueChanged(it)
                        }, onDateValueChanged = {
                            onDateValueChanged(it)
                        }, onDateClicked = {
                            onDateClicked()
                        })
                }
            }
        }
    }
}