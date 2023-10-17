package pl.msiwak.multiplatform.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
class ApiResultRequest(
    val result: Double,
    val amount: Double,
    val date: Instant
)