package pl.msiwak.multiplatform.shared.model

import kotlinx.serialization.Serializable
import pl.msiwak.multiplatform.shared.model.ApiCategorySyncRequest
import pl.msiwak.multiplatform.shared.model.ApiExerciseSyncRequest
import pl.msiwak.multiplatform.shared.model.ApiResultSyncRequest

@Serializable
data class ApiSynchronizationRequest(
    val categoriesRequestModel: List<ApiCategorySyncRequest>,
    val exercisesRequestModel: List<ApiExerciseSyncRequest>,
    val resultsRequestModel: List<ApiResultSyncRequest>
)
