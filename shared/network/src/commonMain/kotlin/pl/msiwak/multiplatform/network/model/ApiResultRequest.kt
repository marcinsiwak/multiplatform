package pl.msiwak.multiplatform.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
class ApiResultRequest(
    val exerciseId: String,
    val result: String,
    val amount: String,
    val date: Instant
)
