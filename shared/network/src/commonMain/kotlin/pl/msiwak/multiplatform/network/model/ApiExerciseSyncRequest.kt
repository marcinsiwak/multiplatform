package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
class ApiExerciseSyncRequest(
    val id: String,
    val categoryId: String,
    val name: String
)
