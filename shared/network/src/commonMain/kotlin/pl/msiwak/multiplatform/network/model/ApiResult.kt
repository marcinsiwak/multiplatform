package pl.msiwak.multiplatform.network.model

import kotlinx.datetime.Instant

@Serializable
class ApiResult(
    val id: String,
    val exerciseId: String,
    val result: String,
    val amount: String,
    val date: Instant
)
