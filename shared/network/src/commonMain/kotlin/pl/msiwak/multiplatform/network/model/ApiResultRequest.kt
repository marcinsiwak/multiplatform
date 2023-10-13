package pl.msiwak.multiplatform.network.model

import kotlinx.datetime.Instant

class ApiResultRequest(
    val result: Double,
    val amount: Double,
    val date: Instant
)
