package pl.msiwak.interfaces.dtos

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class ResultDTO(
    val id: String? = null,
    val exerciseId: String,
    val result: String,
    val amount: String,
    val date: Instant
)
