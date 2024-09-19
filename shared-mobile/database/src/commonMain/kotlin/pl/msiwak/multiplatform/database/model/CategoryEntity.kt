package pl.msiwak.multiplatform.database.model

import pl.msiwak.multiplatform.commonObject.ExerciseType

data class CategoryEntity(
    val id: String = "",
    val name: String = "",
    val exercises: List<ExerciseEntity> = emptyList(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)
