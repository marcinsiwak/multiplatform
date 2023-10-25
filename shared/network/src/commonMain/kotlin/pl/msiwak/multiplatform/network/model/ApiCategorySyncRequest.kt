package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
class ApiCategorySyncRequest(
    val id: String,
    val name: String,
    val exerciseType: String,
    val exercises: List<ApiExercise> = emptyList()
)