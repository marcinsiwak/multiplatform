package pl.msiwak.multiplatform.android.ui.addExercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.components.ResultsTableView
import pl.msiwak.multiplatform.android.ui.widgets.openCalendar
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseEvent
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseViewModel

@Composable
fun AddExerciseScreen(id: Long) {
    val viewModel = koinViewModel<AddExerciseViewModel> { parametersOf(id) }

    val state = viewModel.viewState.collectAsState()
    val context = LocalContext.current

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(viewModel) {
        viewModel.viewEvent.collectLatest { value ->
            when (value) {
                AddExerciseEvent.OpenCalendar -> openCalendar(
                    context = context,
                    onValueChanged = {
                        viewModel.onDatePicked(it)
                    }, onCancelled = {
                        focusManager.clearFocus()
                    })
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            InputView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = state.value.exerciseTitle,
                onValueChange = {
                    viewModel.onExerciseTitleChanged(it)
                },
                hintText = "Exercise"
            )

            ResultsTableView(
                results = state.value.results,
                isNewResultEnabled = state.value.isResultFieldEnabled,
                newResultData = state.value.newResultData,
                onAddNewResultClicked = {
                    viewModel.onAddNewResultClicked()
                },
                onResultValueChanged = {
                    viewModel.onResultValueChanged(it)
                }, onAmountValueChanged = {
                    viewModel.onAmountValueChanged(it)
                }, onDateValueChanged = {
                    viewModel.onDateValueChanged(it)
                }, onDateClicked = {
                    viewModel.onDateClicked()
                })

//            Row {
//                InputView(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(8.dp),
//                    value = state.value.newResult,
//                    onValueChange = {
//                        viewModel.onExerciseNewResultChanged(it)
//                    },
//                    hintText = "Add new result"
//                )
//
//                InputView(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(8.dp)
//                        .focusRequester(focusRequester)
//                        .onFocusChanged {
//                            if (it.hasFocus) {
//                                viewModel.onDateClicked()
//                            }
//                        },
//                    value = state.value.newResultDate,
//                    onValueChange = {},
//                    hintText = "Date",
//                    readOnly = true,
//                )
//            }
//            // dodawanie z poppupa? dodaj byku jeszcze rozpisanie planu treningu (w przyszlosci trener wysyla plan)
//            Button(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 16.dp, horizontal = 80.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.LightGray,
//                    contentColor = Color.Black
//                ),
//                onClick = { viewModel.onAddNewResultClicked() }
//            ) {
//                Text(modifier = Modifier.padding(8.dp), text = "Add result", fontSize = 16.sp)
//            }

//            LazyColumn(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                content = {
//
//                    itemsIndexed(state.value.results) { index: Int, it: FormattedResultData ->
//                        ResultView(
//                            result = it.result,
//                            date = it.date,
//                            amount = it.amount,
//                            onRemove = {
//                                viewModel.onResultRemoved(index)
//                            })
//                    }
//                })
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(vertical = 16.dp, horizontal = 80.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            onClick = { viewModel.onAddNewExerciseClicked() }
        ) {
            Text(modifier = Modifier.padding(8.dp), text = "Add exercise", fontSize = 16.sp)
        }
    }
}