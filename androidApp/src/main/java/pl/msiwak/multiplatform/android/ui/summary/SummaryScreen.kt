package pl.msiwak.multiplatform.android.ui.summary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.utils.OnLifecycleEvent
import pl.msiwak.multiplatform.ui.summary.SummaryViewModel


@Composable
fun SummaryScreen() {
    val viewModel = koinViewModel<SummaryViewModel>()
    val state = viewModel.summaryState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(state.value.categories) { category ->
                    CategoryItem(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clickable {
                                viewModel.onExerciseClicked(category.id)
                            },
                        categoryData = category
                    )
                }
                item {
                    Button(
                        modifier = Modifier.padding(16.dp),
                        border = BorderStroke(2.dp, Color.Black),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        onClick = { viewModel.onAddExerciseClicked() }
                    ) {
                        Row {
                            Icon(
                                modifier = Modifier.padding(8.dp),
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = "Add exercise"
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = "Add exercise"
                            )
                        }
                    }
                }
            })
    }
}

@Preview
@Composable
fun SummaryScreenPreview() {
    SummaryScreen()
}