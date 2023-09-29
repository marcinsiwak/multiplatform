package pl.msiwak.multiplatform.commonObject

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: String = "",
    val name: String = "",
    val exerciseType: ExerciseType = ExerciseType.GYM,
    val exercises: List<Exercise> = emptyList()
)