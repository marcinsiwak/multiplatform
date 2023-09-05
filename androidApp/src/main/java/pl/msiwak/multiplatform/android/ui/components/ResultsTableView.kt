package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.FormattedResultData
import pl.msiwak.multiplatform.commonObject.SortType

@Composable
fun ResultsTableView(
    modifier: Modifier = Modifier,
    resultDataTitles: List<String?> = emptyList(),
    unit: String = "",
    results: List<FormattedResultData> = emptyList(),
    isNewResultEnabled: Boolean,
    sortType: SortType? = null,
    exerciseType: ExerciseType,
    newResultData: FormattedResultData = FormattedResultData(),
    onAddNewResultClicked: () -> Unit = {},
    onLabelClicked: (Int) -> Unit = {},
    onResultValueChanged: (String) -> Unit = {},
    onAmountValueChanged: (String) -> Unit = {},
    onDateValueChanged: (String) -> Unit = {},
    onDateClicked: () -> Unit = {},
    onResultLongClick: (Int) -> Unit = {},
    focusRequesters: List<FocusRequester>
) {
    val listState = rememberLazyListState()

    LaunchedEffect(isNewResultEnabled) {
        if (isNewResultEnabled) {
            listState.animateScrollToItem(0, 0)
            focusRequesters[0].requestFocus()
        }
    }

    Column(
        modifier = modifier
//            .background(colorResource(id = MR.colors.gray_two.resourceId))
    ) {
        // todo results type from database
        Row(
            modifier = Modifier
//                .background(color = colorResource(id = MR.colors.gray.resourceId))
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.dimens.space_8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextWithDrawableView(
                modifier = Modifier
                    .width(MaterialTheme.dimens.first_list_item_size)
                    .offset(x = MaterialTheme.dimens.space_8)
                    .clickable { onLabelClicked(0) },
                text = resultDataTitles.getOrNull(0)?.plus(" [$unit]") ?: "",
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                iconResId = when (sortType) {
                    SortType.RESULT_INCREASING -> R.drawable.ic_arrow_up
                    SortType.RESULT_DECREASING -> R.drawable.ic_arrow_down
                    else -> null
                }
            )
            TextWithDrawableView(
                modifier = Modifier
                    .width(MaterialTheme.dimens.second_list_item_size)
                    .clickable { onLabelClicked(1) },
                text = resultDataTitles.getOrNull(1) ?: "",
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                iconResId = when (sortType) {
                    SortType.AMOUNT_INCREASING -> R.drawable.ic_arrow_up
                    SortType.AMOUNT_DECREASING -> R.drawable.ic_arrow_down
                    else -> null
                }
            )
            TextWithDrawableView(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLabelClicked(2) },
                text = resultDataTitles.getOrNull(2) ?: "",
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                iconResId = when (sortType) {
                    SortType.DATE_INCREASING -> R.drawable.ic_arrow_up
                    SortType.DATE_DECREASING -> R.drawable.ic_arrow_down
                    else -> null
                }
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
                            .padding(MaterialTheme.dimens.space_32)
                            .clickable {
                                onAddNewResultClicked()
                            },
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimary,
                        text = "Add first result"
                    )
                }
            }
            if (isNewResultEnabled) {
                item {
                    NewResultView(
                        modifier = Modifier.focusRequester(focusRequesters[0]),
                        focusRequesters = focusRequesters,
                        exerciseType = exerciseType,
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