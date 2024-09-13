package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
class ApiExercise(
    val id: String? = null,
    val categoryId: String,
    val name: String,
    val exerciseType: String,
    val results: List<ApiResult> = emptyList()
)
