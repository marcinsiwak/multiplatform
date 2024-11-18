package pl.msiwak.multiplatform.shared.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
class ApiResultSyncRequest(
    val id: String,
    val exerciseId: String,
    val result: String,
    val amount: String,
    val date: Instant
)
