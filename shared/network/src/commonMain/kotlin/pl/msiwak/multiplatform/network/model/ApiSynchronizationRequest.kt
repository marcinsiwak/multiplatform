package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiSynchronizationRequest(
    val apiCategoriesRequest: List<ApiCategorySyncRequest>,
    val apiExercisesRequest: List<ApiExerciseSyncRequest>,
    val apiResultsRequest: List<ApiResultSyncRequest>
)