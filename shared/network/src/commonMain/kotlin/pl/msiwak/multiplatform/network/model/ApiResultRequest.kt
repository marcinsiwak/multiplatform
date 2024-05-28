package pl.msiwak.multiplatform.network.model

import kotlinx.datetime.Instant

class ApiResultRequest(
    val exerciseId: String,
    val result: String,
    val amount: String,
    val date: Instant
)
