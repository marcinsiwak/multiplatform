package pl.msiwak.interfaces.dtos

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDTO(
    val id: String? = null,
    val categoryId: String,
    val name: String,
    val exerciseType: String,
    val results: List<ResultDTO> = emptyList()
)
