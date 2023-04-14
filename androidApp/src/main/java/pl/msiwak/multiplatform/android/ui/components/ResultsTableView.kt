package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.colorResource
import org.example.library.MR
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.data.common.FormattedResultData

@Composable
fun ResultsTableView(
    modifier: Modifier = Modifier,
    resultDataTitles: List<String?> = emptyList(),
    unit: String = "",
    results: List<FormattedResultData> = emptyList(),
    isNewResultEnabled: Boolean,
    newResultData: FormattedResultData = FormattedResultData(),
    onAddNewResultClicked: () -> Unit = {},
    onResultValueChanged: (String) -> Unit = {},
    onAmountValueChanged: (String) -> Unit = {},
    onDateValueChanged: (String) -> Unit = {},
    onDateClicked: () -> Unit = {},
    onResultLongClick: (Int) -> Unit = {},
    focusRequesters: List<FocusRequester>
) {
    val dimens = LocalDim.current

    val listState = rememberLazyListState()

    LaunchedEffect(isNewResultEnabled) {
        if (isNewResultEnabled && results.isNotEmpty()) {
            listState.animateScrollToItem(0, 0)
            focusRequesters[0].requestFocus()
        }
    }

    Column(
        modifier = modifier
            .background(colorResource(resource = MR.colors.gray_two))
    ) {
        // todo results type from database
        Row(
            modifier = Modifier
                .background(color = colorResource(resource = MR.colors.gray))
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(vertical = dimens.space_8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .width(dimens.first_list_item_size)
                    .padding(horizontal = dimens.space_8, vertical = dimens.space_16),
                text = resultDataTitles.getOrNull(0)?.plus(" [$unit]") ?: "",
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .width(dimens.second_list_item_size)
                    .padding(horizontal = dimens.space_8, vertical = dimens.space_16),
                text = resultDataTitles.getOrNull(1) ?: "",
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimens.space_8, vertical = dimens.space_16),
                text = resultDataTitles.getOrNull(2) ?: "",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
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
            if (isNewResultEnabled) {
                item {
                    NewResultView(
                        modifier = Modifier.focusRequester(focusRequesters[0]),
                        focusRequesters = focusRequesters,
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
            itemsIndexed(results) { pos, item ->
                ResultView(
                    result = item.result,
                    amount = item.amount,
                    date = item.date,
                    onResultLongClick = {
                        onResultLongClick(pos)
                    })
            }
        }
    }
}