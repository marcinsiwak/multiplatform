package pl.msiwak.multiplatform.android.ui.addExercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.koin.java.KoinJavaComponent
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.ui.addExercise.AddExerciseViewModel

val viewModel: AddExerciseViewModel by KoinJavaComponent.inject(AddExerciseViewModel::class.java)

@Composable
fun AddExerciseScreen() {
    val state = viewModel.viewState.collectAsState()

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
            InputView(
                modifier = Modifier.padding(8.dp),
                value = state.value.newResult,
                onValueChange = {
                    viewModel.onExerciseNewResultChanged(it)
                },
                hintText = "Add new result"
            )

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    items(state.value.results) {
                        Text(color = Color.White, text = it)
                    }
                })
        }

        FloatingActionButton(modifier = Modifier.align(Alignment.BottomEnd), onClick = {
            viewModel.onAddNewExerciseClicked()
        }) {
            Icon(
                tint = Color.White,
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add exercise"
            )
        }
    }
}