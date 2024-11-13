package pl.msiwak.interfaces.dtos

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDTO(
    val id: String? = null,
    val name: String,
    val exerciseType: String,
    val exercises: List<ExerciseDTO> = emptyList()
)
