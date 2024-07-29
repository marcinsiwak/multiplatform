package pl.msiwak.dtos

import kotlinx.serialization.Serializable
import pl.msiwak.util.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class ExerciseDTO(
    val id: String? = null,
    val categoryId: String? = null,
    val name: String,
    val exerciseType: String,
    val results: List<ResultDTO>,
    @Serializable(with = LocalDateTimeSerializer::class)
    val creationDate: LocalDateTime
)
