package pl.msiwak.multiplatform.network.model

class ApiCategoryRequest(
    val name: String,
    val exerciseType: String,
    val exercises: List<ApiExercise> = emptyList()
)
