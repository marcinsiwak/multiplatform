package pl.msiwak.multiplatform.data.entity

import pl.msiwak.multiplatform.data.common.ExerciseShort
import pl.msiwak.multiplatform.data.common.ExerciseType

class CategoryData(
    val id: Long,
    val name: String,
    val exercises: List<ExerciseShort>,
    val exerciseType: ExerciseType
)