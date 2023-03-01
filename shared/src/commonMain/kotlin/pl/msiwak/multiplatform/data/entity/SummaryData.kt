package pl.msiwak.multiplatform.data.entity

import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.ResultData

data class SummaryData(
    val id: Long = 0,
    val categoryId: Long = 0,
    val exerciseTitle: String = "",
    val results: List<ResultData> = emptyList(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)