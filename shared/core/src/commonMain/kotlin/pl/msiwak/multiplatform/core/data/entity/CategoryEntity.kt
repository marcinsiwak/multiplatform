package pl.msiwak.multiplatform.core.data.entity

import pl.msiwak.multiplatform.core.data.common.ExerciseType

data class CategoryEntity(
    val id: String = "",
    val name: String = "",
    val exercises: List<ExerciseEntity> = emptyList(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)