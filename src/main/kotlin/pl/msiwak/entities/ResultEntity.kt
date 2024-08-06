package pl.msiwak.entities

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class ResultEntity(
    val id: String? = null,
    val amount: String,
    val result: String,
    val date: LocalDateTime
)
