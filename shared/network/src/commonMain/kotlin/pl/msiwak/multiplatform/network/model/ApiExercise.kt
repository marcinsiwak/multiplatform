package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
class ApiExercise(
    val exerciseId: String,
    val categoryId: String,
    val name: String
)