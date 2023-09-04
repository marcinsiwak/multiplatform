package pl.msiwak.multiplatform.core.data.entity

import pl.msiwak.multiplatform.core.data.common.ExerciseType
import pl.msiwak.multiplatform.core.data.common.ResultData

data class ExerciseEntity(
    val id: String = "",
    val categoryId: String = "",
    val exerciseTitle: String = "",
    val results: List<ResultData> = emptyList(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)