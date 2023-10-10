package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
class ApiCategoryRequest(
    val name: String,
    val exerciseType: String,
    val exercises: List<ApiExercise> = emptyList()
)