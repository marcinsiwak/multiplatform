package pl.msiwak.multiplatform.database.model

import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.ResultData

data class ExerciseEntity(
    val id: String = "",
    val categoryId: String = "",
    val exerciseTitle: String = "",
    val results: List<ResultData> = emptyList(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)
