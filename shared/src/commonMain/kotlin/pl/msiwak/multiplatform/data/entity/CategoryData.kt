package pl.msiwak.multiplatform.data.entity

import pl.msiwak.multiplatform.data.common.ExerciseShort
import pl.msiwak.multiplatform.data.common.ExerciseType

data class CategoryData(
    val id: Long = 0,
    val name: String = "",
    val exercises: List<ExerciseShort> = emptyList(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)