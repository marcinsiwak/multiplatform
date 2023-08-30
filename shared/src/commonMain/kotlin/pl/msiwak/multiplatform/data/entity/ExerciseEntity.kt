package pl.msiwak.multiplatform.data.entity

import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.ResultData

data class ExerciseEntity(
    val id: String = "",
    val categoryId: String = "",
    val exerciseTitle: String = "",
    val results: List<ResultData> = emptyList(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)