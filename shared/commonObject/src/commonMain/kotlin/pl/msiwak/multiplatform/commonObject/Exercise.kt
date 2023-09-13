package pl.msiwak.multiplatform.commonObject

@kotlinx.serialization.Serializable
data class Exercise(
    val id: String = "",
    val categoryId: String = "",
    var exerciseTitle: String = "",
    val results: List<ResultData> = emptyList(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)
