package pl.msiwak.multiplatform.ui.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.add_new_result
import athletetrack.shared.commonresources.generated.resources.add_result_save
import athletetrack.shared.commonresources.generated.resources.confirm
import athletetrack.shared.commonresources.generated.resources.ic_chart
import athletetrack.shared.commonresources.generated.resources.no
import athletetrack.shared.commonresources.generated.resources.remove_result_dialog_description
import athletetrack.shared.commonresources.generated.resources.remove_result_dialog_title
import athletetrack.shared.commonresources.generated.resources.yes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonObject.DateFilterType
import pl.msiwak.multiplatform.commonObject.extenstion.serializeToJson
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.ResultsTableView
import pl.msiwak.multiplatform.ui.commonComponent.ResultsTimeFilterView
import pl.msiwak.multiplatform.ui.commonComponent.RunningTimeInputDialog
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview
import pl.msiwak.multiplatform.ui.commonComponent.util.OnLifecycleEvent

private const val FOCUS_REQUESTERS_AMOUNT = 4

@Composable
fun ExerciseScreen(
    navController: NavController,
    id: String,
    viewModel: ExerciseViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    val focusManager = LocalFocusManager.current
    val focusRequesters = List(FOCUS_REQUESTERS_AMOUNT) { remember { FocusRequester() } }

    OnLifecycleEvent { _, event ->
        if (event == Lifecycle.Event.ON_PAUSE) {
            viewModel.onPause()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onInit(id)
        viewModel.viewEvent.collectLatest { value ->
            when (value) {
                is ExerciseEvent.FocusOnInput -> focusRequesters[value.pos].requestFocus()
            }
        }
    }

    ExerciseScreenContent(
        id = id,
        navController = navController,
        viewState = viewState,
        focusManager = focusManager,
        focusRequesters = focusRequesters,
        onResultRemoved = viewModel::onResultRemoved,
        onPopupDismissed = viewModel::onPopupDismissed,
        onConfirmRunningAmount = viewModel::onConfirmRunningAmount,
        onDismissAmountDialog = viewModel::onDismissAmountDialog,
        onTabClicked = viewModel::onTabClicked,
        onSaveResultClicked = viewModel::onSaveResultClicked,
        onAddNewResultClicked = viewModel::onAddNewResultClicked,
        onResultValueChanged = viewModel::onResultValueChanged,
        onAmountValueChanged = viewModel::onAmountValueChanged,
        onDateValueChanged = viewModel::onDateValueChanged,
        onDateClicked = viewModel::onDateClicked,
        onResultLongClicked = viewModel::onResultLongClicked,
        onLabelClicked = viewModel::onLabelClicked,
        onAmountClicked = viewModel::onAmountClicked,
        onDateConfirmClicked = viewModel::onDatePicked,
        onDateDismiss = viewModel::onDateDismiss
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalResourceApi::class)
@Composable
fun ExerciseScreenContent(
    id: String,
    navController: NavController,
    viewState: State<ExerciseState>,
    focusManager: FocusManager,
    focusRequesters: List<FocusRequester>,
    onResultRemoved: () -> Unit = {},
    onPopupDismissed: () -> Unit = {},
    onConfirmRunningAmount: (String, String, String, String) -> Unit = { _, _, _, _ -> },
    onDismissAmountDialog: () -> Unit = {},
    onTabClicked: (DateFilterType) -> Unit = {},
    onSaveResultClicked: () -> Unit = {},
    onAddNewResultClicked: () -> Unit = {},
    onResultValueChanged: (String) -> Unit = {},
    onAmountValueChanged: (String) -> Unit = {},
    onDateValueChanged: (String) -> Unit = {},
    onDateClicked: () -> Unit = {},
    onResultLongClicked: (Int) -> Unit = {},
    onLabelClicked: (Int) -> Unit = {},
    onAmountClicked: () -> Unit = {},
    onDateConfirmClicked: (Long?) -> Unit = {},
    onDateDismiss: () -> Unit = {}
) {
    val datePickerState = rememberDatePickerState()

    if (viewState.value.isDatePickerVisible) {
        DatePickerDialog(
            onDismissRequest = onDateDismiss,
            confirmButton = {
                MainButton(
                    text = stringResource(Res.string.confirm),
                    onClick = {
                        onDateConfirmClicked(datePickerState.selectedDateMillis)
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
                onResultRemoved()
            },
            onDismissClicked = {
                onPopupDismissed()
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
                onConfirmRunningAmount(hours, minutes, seconds, milliseconds)
            },
            onDismiss = {
                onDismissAmountDialog()
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
                    .padding(top = it.calculateTopPadding())
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Top
            ) {
                ResultsTimeFilterView(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(bottom = MaterialTheme.dimens.space_16),
                    tabs = viewState.value.filter,
                    selectedPos = viewState.value.selectedFilterPosition,
                    onTabClicked = {
                        onTabClicked(it)
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(
                                NavDestination.ExerciseDestination.NavExerciseChartScreen.route(
                                    exerciseId = id,
                                    exerciseType = viewState.value.exerciseType.serializeToJson()
                                )
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_chart),
                            contentDescription = null
                        )
                    }

                    if (!viewState.value.isResultFieldEnabled) {
                        Button(
                            modifier = Modifier
                                .padding(bottom = MaterialTheme.dimens.space_16)
                                .padding(horizontal = MaterialTheme.dimens.space_16),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.tertiary,
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                            onClick = {
                                onAddNewResultClicked()
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
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                            onClick = {
                                onSaveResultClicked()
                            }
                        ) {
                            Text(
                                text = stringResource(Res.string.add_result_save),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                ResultsTableView(
                    modifier = Modifier,
                    resultDataTitles = viewState.value.resultDataTitles,
                    results = viewState.value.results,
                    exerciseType = viewState.value.exerciseType,
                    focusRequesters = focusRequesters,
                    isNewResultEnabled = viewState.value.isResultFieldEnabled,
                    newResultData = viewState.value.newResultData,
                    onAddNewResultClicked = onAddNewResultClicked::invoke,
                    onResultValueChanged = onResultValueChanged::invoke,
                    onAmountValueChanged = onAmountValueChanged::invoke,
                    onDateValueChanged = onDateValueChanged::invoke,
                    onDateClicked = onDateClicked::invoke,
                    onResultLongClick = onResultLongClicked::invoke,
                    onLabelClicked = onLabelClicked::invoke,
                    onAmountClicked = onAmountClicked::invoke
                )
            }
        }
    )
}

@DarkLightPreview
@Composable
fun ExerciseScreenPreview() {
    AppTheme {
        ExerciseScreenContent(
            id = "",
            rememberNavController(),
            viewState = MutableStateFlow(ExerciseState()).collectAsState(),
            focusManager = LocalFocusManager.current,
            focusRequesters = listOf()
        )
    }
}
