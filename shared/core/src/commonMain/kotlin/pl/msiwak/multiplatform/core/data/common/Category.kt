package pl.msiwak.multiplatform.core.data.common

data class Category(
    val id: String = "",
    val name: String = "",
    val exerciseType: ExerciseType = ExerciseType.GYM,
    val exercises: List<Exercise> = emptyList()
)