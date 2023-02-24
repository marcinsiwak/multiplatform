package pl.msiwak.multiplatform.android.ui.exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.components.ResultView
import pl.msiwak.multiplatform.android.ui.widgets.openCalendar
import pl.msiwak.multiplatform.data.common.FormattedResultData
import pl.msiwak.multiplatform.ui.exercise.ExerciseEvent
import pl.msiwak.multiplatform.ui.exercise.ExerciseViewModel

@Composable
fun ExerciseScreen(id: Long) {
    val viewModel = koinViewModel<ExerciseViewModel> { parametersOf(id) }

    val state = viewModel.viewState.collectAsState()
    val context = LocalContext.current

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(viewModel) {
        viewModel.viewEvent.collectLatest { value ->
            when (value) {
                ExerciseEvent.OpenCalendar -> openCalendar(
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
                modifier = Modifier.padding(8.dp),
                value = state.value.exerciseTitle,
                onValueChange = {
                    viewModel.onExerciseTitleChanged(it)
                },
                hintText = "Exercise"
            )

            Text(text = "RESULTS", color = Color.White)
            Row {
                InputView(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    value = state.value.newResult,
                    onValueChange = {
                        viewModel.onExerciseNewResultChanged(it)
                    },
                    hintText = "Add new result"
                )

                InputView(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            if (it.hasFocus) {
                                viewModel.onDateClicked()
                            }
                        },
                    value = state.value.newResultDate,
                    onValueChange = {},
                    hintText = "Date",
                    readOnly = true,
                )
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                        .clickable { viewModel.onAddNewResultClicked() },
                    tint = Color.White,
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add result"
                )
            }

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    itemsIndexed(state.value.results) { index: Int, it: FormattedResultData ->
                        ResultView(
                            result = it.result,
                            date = it.date,
                            amount = it.amount,
                            onRemove = {
                                viewModel.onResultRemoved(index)
                            })
                    }
                })
        }
    }
}
