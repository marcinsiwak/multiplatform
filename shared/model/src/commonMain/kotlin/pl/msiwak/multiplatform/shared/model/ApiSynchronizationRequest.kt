package pl.msiwak.multiplatform.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiSynchronizationRequest(
    val categories: List<ApiCategory>,
    val exercises: List<ApiExercise>,
    val results: List<ApiResult>
)
