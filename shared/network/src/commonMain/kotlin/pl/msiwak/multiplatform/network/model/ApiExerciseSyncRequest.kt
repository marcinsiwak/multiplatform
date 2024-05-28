package pl.msiwak.multiplatform.network.model

@Serializable
class ApiExerciseSyncRequest(
    val id: String,
    val categoryId: String,
    val name: String
)
