package pl.msiwak.multiplatform.ui.exercise

import pl.msiwak.multiplatform.data.common.FormattedResultData

data class ExerciseState(
    val exerciseTitle: String = "",
    val newResult: String = "",
    val newResultDate: String = "",
    val results: List<FormattedResultData> = listOf(),
)