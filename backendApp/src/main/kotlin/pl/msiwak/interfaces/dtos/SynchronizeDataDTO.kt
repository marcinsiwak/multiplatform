package pl.msiwak.interfaces.dtos

import kotlinx.serialization.Serializable

@Serializable
data class SynchronizeDataDTO(
    val categories: List<CategoryDTO>,
    val exercises: List<ExerciseDTO>,
    val results: List<ResultDTO>
)
