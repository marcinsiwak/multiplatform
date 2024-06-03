package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
class ApiUpdateExerciseNameRequest(
    val exerciseId: String,
    val name: String
)
