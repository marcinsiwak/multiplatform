package pl.msiwak.multiplatform.ui.commonComponent

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.style.TextAlign
import dev.icerock.moko.resources.compose.stringResource
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.FormattedResultData
import pl.msiwak.multiplatform.commonObject.ResultTableItemData
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens

@Composable
fun ResultsTableView(
    modifier: Modifier = Modifier,
    resultDataTitles: List<ResultTableItemData> = emptyList(),
    results: List<FormattedResultData> = emptyList(),
    isNewResultEnabled: Boolean,
    exerciseType: ExerciseType,
    newResultData: FormattedResultData = FormattedResultData(),
    onAddNewResultClicked: () -> Unit = {},
    onLabelClicked: (Int) -> Unit = {},
    onResultValueChanged: (String) -> Unit = {},
    onAmountValueChanged: (String) -> Unit = {},
    onDateValueChanged: (String) -> Unit = {},
    onDateClicked: () -> Unit = {},
    onAmountClicked: () -> Unit = {},
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
    ) {
        Row(
            modifier = Modifier
//                .background(color = colorResource(id = SR.colors.gray.resourceId))
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.dimens.space_8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            resultDataTitles.forEachIndexed { index, item ->
                TextWithDrawableView(
                    modifier = Modifier
                        .clickable { onLabelClicked(index) }
                        .width(MaterialTheme.dimens.result_item_width)
                        .padding(horizontal = MaterialTheme.dimens.space_24),
                    text = item.text,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    iconResId = when (item.isArrowUp) {
                        true -> SR.images.ic_arrow_up
                        false -> SR.images.ic_arrow_down
                        null -> null
                    }
                )
            }
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
                        text = stringResource(SR.strings.add_new_result)
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
                        },
                        onAmountClicked = {
                            onAmountClicked()
                        }
                    )
                }
            }
            itemsIndexed(results) { pos, item ->
                ResultView(
                    details = listOf(item.result, item.amount, item.date),
                    onResultLongClick = {
                        onResultLongClick(pos)
                    }
                )
            }
        }
    }
}
