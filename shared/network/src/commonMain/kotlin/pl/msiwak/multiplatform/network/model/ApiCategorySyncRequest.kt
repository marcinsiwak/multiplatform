package pl.msiwak.multiplatform.network.model

class ApiCategorySyncRequest(
    val id: String,
    val name: String,
    val exerciseType: String,
    val exercises: List<ApiExercise> = emptyList()
)
