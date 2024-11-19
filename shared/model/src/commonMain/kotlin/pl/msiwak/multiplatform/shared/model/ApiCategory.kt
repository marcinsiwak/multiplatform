package pl.msiwak.multiplatform.shared.model

import kotlinx.serialization.Serializable

@Serializable
class ApiCategory(
    val id: String? = null,
    val name: String,
    val exerciseType: String,
    val exercises: List<ApiExercise> = emptyList()
)
