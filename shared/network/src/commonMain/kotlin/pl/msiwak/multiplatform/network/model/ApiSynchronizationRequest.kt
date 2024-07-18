package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiSynchronizationRequest(
    val categoriesRequestModel: List<ApiCategorySyncRequest>,
    val exercisesRequestModel: List<ApiExerciseSyncRequest>,
    val resultsRequestModel: List<ApiResultSyncRequest>
)
