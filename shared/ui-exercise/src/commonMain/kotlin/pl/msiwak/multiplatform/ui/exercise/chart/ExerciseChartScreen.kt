package pl.msiwak.multiplatform.ui.exercise.chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aay.compose.barChart.BarChart
import com.aay.compose.barChart.model.BarParameters
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview
import pl.msiwak.multiplatform.ui.commonComponent.util.OnLifecycleEvent

@Composable
fun ExerciseChartScreen(
    exerciseId: String,
    exerciseType: ExerciseType,
    navController: NavController,
    viewModel: ExerciseChartViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> viewModel.onInit(exerciseId, exerciseType)
            else -> Unit
        }
    }

    ExerciseChartScreenContent(navController, viewState)
}

@Composable
private fun ExerciseChartScreenContent(
    navController: NavController,
    viewState: State<ExerciseChartState>
) {
    Scaffold(
        topBar = {
            AppBar(navController = navController, title = "chart")
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
            ) {
                if (viewState.value.results.isNotEmpty()) {
                    val dates = viewState.value.results.map { it.date }

                    BarChart(
                        chartParameters = listOf(
                            BarParameters(
                                dataName = "klata",
                                data = viewState.value.results.map { it.result },
                                barColor = Color.LightGray
                            )
                        ),
                        xAxisData = dates,
                        yAxisRange = 15,
                        showGridWithSpacer = true,
                        isShowGrid = true
                    )
                }
            }
        }
    )
}

@DarkLightPreview
@Composable
private fun ExerciseChartScreenPreview() {
    AppTheme {
        ExerciseChartScreenContent(
            navController = rememberNavController(),
            viewState = MutableStateFlow(ExerciseChartState()).collectAsState()
        )
    }
}
