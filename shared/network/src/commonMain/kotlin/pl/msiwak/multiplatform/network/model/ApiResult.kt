package pl.msiwak.multiplatform.network.model

import kotlinx.datetime.Instant

class ApiResult(
    val id: String,
    val exerciseId: String,
    val result: Double,
    val amount: Double,
    val date: Instant
)