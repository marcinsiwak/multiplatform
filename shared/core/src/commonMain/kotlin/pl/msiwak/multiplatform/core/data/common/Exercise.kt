package pl.msiwak.multiplatform.core.data.common

@kotlinx.serialization.Serializable
data class Exercise(
    val id: String = "",
    val categoryId: String = "",
    var exerciseTitle: String = "",
    val results: List<ResultData> = emptyList(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)
