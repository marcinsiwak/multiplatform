package pl.msiwak.multiplatform.network.model

data class ApiSynchronizationRequest(
    val apiCategoriesRequest: List<ApiCategorySyncRequest>,
    val apiExercisesRequest: List<ApiExerciseSyncRequest>,
    val apiResultsRequest: List<ApiResultSyncRequest>
)
