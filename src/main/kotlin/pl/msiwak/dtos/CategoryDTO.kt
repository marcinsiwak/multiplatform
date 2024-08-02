package pl.msiwak.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val id: String? = null,
    val name: String,
    val exerciseType: String,
    val exercises: List<ExerciseDTO> = emptyList()
//    @Serializable(with = LocalDateTimeSerializer::class)
//    val creationDate: LocalDateTime
)
