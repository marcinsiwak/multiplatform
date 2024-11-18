package pl.msiwak.multiplatform.shared.model

import kotlinx.serialization.Serializable

@Serializable
class ApiExerciseSyncRequest(
    val id: String,
    val categoryId: String,
    val name: String
)
