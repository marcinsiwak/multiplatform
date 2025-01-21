package pl.msiwak.multiplatform.ui.addExercise

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.util.fastForEachIndexed
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared_mobile.commonresources.generated.resources.Res
import athletetrack.shared_mobile.commonresources.generated.resources.add_new_result
import athletetrack.shared_mobile.commonresources.generated.resources.add_result_save
import athletetrack.shared_mobile.commonresources.generated.resources.confirm
import athletetrack.shared_mobile.commonresources.generated.resources.ic_arrow_down
import athletetrack.shared_mobile.commonresources.generated.resources.ic_arrow_up
import athletetrack.shared_mobile.commonresources.generated.resources.no
import athletetrack.shared_mobile.commonresources.generated.resources.remove_result_dialog_description
import athletetrack.shared_mobile.commonresources.generated.resources.remove_result_dialog_title
import athletetrack.shared_mobile.commonresources.generated.resources.yes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.NewResultView
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.ResultView
import pl.msiwak.multiplatform.ui.commonComponent.ResultsTimeFilterView
import pl.msiwak.multiplatform.ui.commonComponent.RunningTimeInputDialog
import pl.msiwak.multiplatform.ui.commonComponent.TextWithDrawableView
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview
import pl.msiwak.multiplatform.ui.commonComponent.util.OnLifecycleEvent

private const val FOCUS_REQUESTERS_AMOUNT = 4

@Composable
fun AddExerciseScreen(
    navController: NavController,
    id: String,
    viewModel: AddExerciseViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    val focusManager = LocalFocusManager.current
    val focusRequesters = List(FOCUS_REQUESTERS_AMOUNT) { remember { FocusRequester() } }

//    OnLifecycleEvent { _, event ->
//        if (event == Lifecycle.Event.ON_PAUSE) {
//            viewModel.onPause()
//        }
//    }

    LaunchedEffect(Unit) {
        viewModel.onInit(id)
        viewModel.viewEvent.collectLatest { value ->
            when (value) {
                is AddExerciseEvent.FocusOnInput -> focusRequesters[value.pos].requestFocus()
            }
        }
    }

    AddExerciseScreenContent(
        navController = navController,
        viewState = viewState,
        focusManager = focusManager,
        focusRequesters = focusRequesters,
        onUiAction = viewModel::onUiAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExerciseScreenContent(
    navController: NavController,
    viewState: State<AddExerciseState>,
    focusManager: FocusManager,
    focusRequesters: List<FocusRequester>,
    onUiAction: (AddExerciseUiAction) -> Unit
) {
    val datePickerState = rememberDatePickerState()

    if (viewState.value.isDatePickerVisible) {
        DatePickerDialog(
            onDismissRequest = { onUiAction(AddExerciseUiAction.OnDateDismiss) },
            confirmButton = {
                MainButton(
                    text = stringResource(Res.string.confirm),
                    onClick = {
                        onUiAction(AddExerciseUiAction.OnDateConfirmClicked(datePickerState.selectedDateMillis))
                    }
                )
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (viewState.value.isRemoveExerciseDialogVisible) {
        PopupDialog(
            title = stringResource(Res.string.remove_result_dialog_title),
            description = stringResource(Res.string.remove_result_dialog_description),
            confirmButtonTitle = stringResource(Res.string.yes),
            dismissButtonTitle = stringResource(Res.string.no),
            onConfirmClicked = {
                onUiAction(AddExerciseUiAction.OnResultRemoved)
            },
            onDismissClicked = {
                onUiAction(AddExerciseUiAction.OnPopupDismissed)
            }
        )
    }

    if (viewState.value.isLoading) {
        Loader()
    }

    if (viewState.value.isTimeInputDialogVisible) {
        focusManager.clearFocus()
        RunningTimeInputDialog(
            onConfirm = { hours: String, minutes: String, seconds: String, milliseconds: String ->
                onUiAction(
                    AddExerciseUiAction.OnConfirmRunningAmount(
                        hours,
                        minutes,
                        seconds,
                        milliseconds
                    )
                )
            },
            onDismiss = {
                onUiAction(AddExerciseUiAction.OnDismissAmountDialog)
            }
        )
    }

    Scaffold(
        topBar = {
            AppBar(navController = navController, title = viewState.value.exerciseTitle)
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary
                            )
                        )
                    )
                    .padding(top = it.calculateTopPadding()),
                verticalArrangement = Arrangement.Top
            ) {
                ResultsTimeFilterView(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(bottom = MaterialTheme.dimens.space_16),
                    tabs = viewState.value.filter,
                    selectedPos = viewState.value.selectedFilterPosition,
                    onTabClicked = {
                        onUiAction(AddExerciseUiAction.OnTabClicked(it))
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    if (!viewState.value.isResultFieldEnabled) {
                        Button(
                            modifier = Modifier
                                .padding(bottom = MaterialTheme.dimens.space_16)
                                .padding(horizontal = MaterialTheme.dimens.space_16),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.onTertiary
                            ),
                            onClick = {
                                onUiAction(AddExerciseUiAction.OnAddNewResultClicked)
                            }
                        ) {
                            Text(
                                text = stringResource(Res.string.add_new_result),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    } else {
                        Button(
                            modifier = Modifier
                                .padding(bottom = MaterialTheme.dimens.space_16)
                                .padding(horizontal = MaterialTheme.dimens.space_16),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.onTertiary
                            ),
                            onClick = {
                                onUiAction(AddExerciseUiAction.OnSaveResultClicked)
                            }
                        ) {
                            Text(
                                text = stringResource(Res.string.add_result_save),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                val listState = rememberLazyListState()

                LaunchedEffect(viewState.value.isResultFieldEnabled) {
                    if (viewState.value.isResultFieldEnabled) {
                        listState.animateScrollToItem(0, 0)
                        focusRequesters[0].requestFocus()
                    }
                }

                Column {
                    Row(
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.dimens.space_8),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        viewState.value.resultDataTitles.fastForEachIndexed { index, item ->
                            TextWithDrawableView(
                                modifier = Modifier
                                    .clickable { onUiAction(AddExerciseUiAction.OnLabelClicked(index)) }
                                    .width(MaterialTheme.dimens.result_item_width),
                                text = item.text,
                                color = MaterialTheme.colorScheme.onPrimary,
                                textAlign = TextAlign.Center,
                                iconResId = when (item.isArrowUp) {
                                    true -> Res.drawable.ic_arrow_up
                                    false -> Res.drawable.ic_arrow_down
                                    null -> null
                                }
                            )
                        }
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxHeight(),
                        state = listState
                    ) {
                        if (viewState.value.results.isEmpty() && !viewState.value.isResultFieldEnabled) {
                            item {
                                Text(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(MaterialTheme.dimens.space_32)
                                        .clickable {
                                            onUiAction(AddExerciseUiAction.OnAddNewResultClicked)
                                        },
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    text = stringResource(Res.string.add_new_result)
                                )
                            }
                        }
                        if (viewState.value.isResultFieldEnabled) {
                            item {
                                NewResultView(
                                    modifier = Modifier.focusRequester(focusRequesters[0]),
                                    focusRequesters = focusRequesters,
                                    exerciseType = viewState.value.exerciseType,
                                    newResultData = viewState.value.newResultData,
                                    onResultValueChanged = {
                                        onUiAction(AddExerciseUiAction.OnResultValueChanged(it))
                                    }, onAmountValueChanged = {
                                        onUiAction(AddExerciseUiAction.OnAmountValueChanged(it))
                                    }, onDateValueChanged = {
                                        onUiAction(AddExerciseUiAction.OnDateValueChanged(it))
                                    }, onDateClicked = {
                                        onUiAction(AddExerciseUiAction.OnDateClicked)
                                    },
                                    onAmountClicked = {
                                        onUiAction(AddExerciseUiAction.OnAmountClicked)
                                    }
                                )
                            }
                        }

                        itemsIndexed(viewState.value.results) { pos, item ->
                            ResultView(
                                details = listOf(item.result, item.amount, item.date),
                                onResultLongClick = {
                                    onUiAction(AddExerciseUiAction.OnResultLongClicked(pos))
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}

@DarkLightPreview
@Composable
fun AddExerciseScreenPreview() {
    AppTheme {
        AddExerciseScreenContent(
            rememberNavController(),
            viewState = MutableStateFlow(AddExerciseState()).collectAsState(),
            focusManager = LocalFocusManager.current,
            focusRequesters = listOf(),
            onUiAction = {}
        )
    }
}
