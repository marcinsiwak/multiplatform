package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.ui.extensions.bottomBorder
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.data.common.FormattedResultData

@Composable
fun ResultsTableView(
    modifier: Modifier = Modifier,
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

    val focusRequester = remember { FocusRequester() }

    val listState = rememberLazyListState()

    LaunchedEffect(isNewResultEnabled) {
        if (isNewResultEnabled) {
            listState.animateScrollToItem(results.lastIndex, 100)
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = modifier
    ) {
        // todo results type from database
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .bottomBorder(3.dp, Color.LightGray)
                .padding(vertical = dimens.space_8)
        ) {
            Text(
                modifier = Modifier
                    .width(dimens.space_96)
                    .padding(horizontal = dimens.space_8, vertical = dimens.space_16),
                text = "Weight",
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .width(dimens.space_96)
                    .padding(horizontal = dimens.space_8, vertical = dimens.space_16),
                text = "Reps",
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.space_8, vertical = dimens.space_16),
                text = "Date",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            modifier = Modifier.height(400.dp),
            state = listState
        ) {
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

            if (isNewResultEnabled) {
                item {
                    NewResultView(
                        modifier = Modifier.focusRequester(focusRequester),
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