package pl.msiwak.multiplatform.api.data.user

import kotlinx.serialization.Serializable

@Serializable
class ApiExercise(
    val exerciseId: String,
    val name: String,
)