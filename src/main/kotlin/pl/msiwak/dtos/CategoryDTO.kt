package pl.msiwak.dtos

import kotlinx.serialization.Serializable
import pl.msiwak.util.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class CategoryDTO(
    val id: String? = null,
    val name: String,
    val exerciseType: String,
    val exercises: List<ExerciseDTO> = emptyList(),
    @Serializable(with = LocalDateTimeSerializer::class)
    val creationDate: LocalDateTime
)
