package pl.msiwak.dtos

import kotlinx.serialization.Serializable
import pl.msiwak.util.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class ResultDTO(
    val id: String? = null,
    val exerciseId: String,
    val result: String,
    val amount: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    val date: LocalDateTime
)
