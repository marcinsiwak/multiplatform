package pl.msiwak.multiplatform.data.entity

import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.ResultData

data class SummaryEntity(
    val id: Long = 0,
    val exerciseTitle: String,
    val results: List<ResultData>,
    val exerciseType: ExerciseType
)