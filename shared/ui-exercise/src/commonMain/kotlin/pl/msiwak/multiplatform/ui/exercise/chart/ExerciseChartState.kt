package pl.msiwak.multiplatform.ui.exercise.chart

import pl.msiwak.multiplatform.commonObject.ChartResultData

data class ExerciseChartState(
    val results: List<ChartResultData> = emptyList()
)
