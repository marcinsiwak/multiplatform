package pl.msiwak.multiplatform.data.common

data class Exercise(
    val id: String = "",
    val categoryId: String = "",
    val exerciseTitle: String = "",
    val results: List<ResultData> = emptyList(),
    val exerciseType: ExerciseType = ExerciseType.GYM
)
