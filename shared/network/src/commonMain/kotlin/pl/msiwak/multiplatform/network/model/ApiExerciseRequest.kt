package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
class ApiExerciseRequest(
    val categoryId: String,
    val name: String
)