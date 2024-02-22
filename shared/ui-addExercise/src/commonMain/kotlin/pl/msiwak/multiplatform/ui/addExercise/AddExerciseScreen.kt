package pl.msiwak.multiplatform.ui.addExercise

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import pl.msiwak.multiplatform.commonObject.DateFilterType
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.InputView
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.ResultsTableView
import pl.msiwak.multiplatform.ui.commonComponent.ResultsTimeFilterView
import pl.msiwak.multiplatform.ui.commonComponent.RunningTimeInputDialog

private const val FOCUS_REQUESTERS_AMOUNT = 4

@Composable
fun AddExerciseScreen(
    id: String,
    viewModel: AddExerciseViewModel = koinInject { parametersOf(id) }
) {
    val viewState = viewModel.viewState.collectAsState()

//    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val focusRequesters = List(FOCUS_REQUESTERS_AMOUNT) { remember { FocusRequester() } }

//    OnLifecycleEvent { _, event ->
//        when (event) {
//            Lifecycle.Event.ON_PAUSE -> viewModel.onPause()
//            else -> Unit
//        }
//    }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collectLatest { value ->
            when (value) {
                AddExerciseEvent.OpenCalendar -> {
                    focusManager.clearFocus()
//                    openCalendar(
//                        context = context,
//                        onValueChanged = {
//                            viewModel.onDatePicked(it)
//                        }
//                    )
                }

                is AddExerciseEvent.FocusOnInput -> {
                    focusRequesters[value.pos].requestFocus()
//                    val errorMsg = context.getString(SR.strings.input_wrong_format)
//                    Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    AddExerciseScreenContent(
        viewState = viewState,
        focusManager = focusManager,
        focusRequesters = focusRequesters,
        onResultRemoved = viewModel::onResultRemoved,
        onPopupDismissed = viewModel::onPopupDismissed,
        onConfirmRunningAmount = viewModel::onConfirmRunningAmount,
        onDismissAmountDialog = viewModel::onDismissAmountDialog,
        onExerciseTitleChanged = viewModel::onExerciseTitleChanged,
        onTitleClicked = viewModel::onTitleClicked,
        onTabClicked = viewModel::onTabClicked,
        onSaveResultClicked = viewModel::onSaveResultClicked,
        onAddNewResultClicked = viewModel::onAddNewResultClicked,
        onResultValueChanged = viewModel::onResultValueChanged,
        onAmountValueChanged = viewModel::onAmountValueChanged,
        onDateValueChanged = viewModel::onDateValueChanged,
        onDateClicked = viewModel::onDateClicked,
        onResultLongClicked = viewModel::onResultLongClicked,
        onLabelClicked = viewModel::onLabelClicked,
        onAmountClicked = viewModel::onAmountClicked
    )
}

@Composable
fun AddExerciseScreenContent(
    viewState: State<AddExerciseState>,
    focusManager: FocusManager,
    focusRequesters: List<FocusRequester>,
    onResultRemoved: () -> Unit = {},
    onPopupDismissed: () -> Unit = {},
    onConfirmRunningAmount: (String, String, String, String) -> Unit = { _, _, _, _ -> },
    onDismissAmountDialog: () -> Unit = {},
    onExerciseTitleChanged: (String) -> Unit = {},
    onTitleClicked: () -> Unit = {},
    onTabClicked: (DateFilterType) -> Unit = {},
    onSaveResultClicked: () -> Unit = {},
    onAddNewResultClicked: () -> Unit = {},
    onResultValueChanged: (String) -> Unit = {},
    onAmountValueChanged: (String) -> Unit = {},
    onDateValueChanged: (String) -> Unit = {},
    onDateClicked: () -> Unit = {},
    onResultLongClicked: (Int) -> Unit = {},
    onLabelClicked: (Int) -> Unit = {},
    onAmountClicked: () -> Unit = {}
) {
    if (viewState.value.isRemoveExerciseDialogVisible) {
        PopupDialog(
            title = stringResource(SR.strings.remove_result_dialog_title),
            description = stringResource(SR.strings.remove_result_dialog_description),
            confirmButtonTitle = stringResource(SR.strings.yes),
            dismissButtonTitle = stringResource(SR.strings.no),
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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        if (viewState.value.isEditNameEnabled) {
            InputView(
                value = viewState.value.exerciseTitle,
                onValueChange = {
                    onExerciseTitleChanged(it)
                }
            )
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.dimens.space_12,
                        end = MaterialTheme.dimens.space_24
                    )
                    .padding(vertical = MaterialTheme.dimens.space_16)
                    .clickable { onTitleClicked() },
                text = viewState.value.exerciseTitle,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

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
                        text = stringResource(SR.strings.add_new_result),
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
                        text = stringResource(SR.strings.add_result_save),
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

//@DarkLightPreview
//@Composable
//fun AddExerciseScreenPreview() {
//    AppTheme {
//        AddExerciseScreenContent(
//            viewState = MutableStateFlow(AddExerciseState()).collectAsState(),
//            focusManager = LocalFocusManager.current,
//            focusRequesters = listOf()
//        )
//    }
//}
