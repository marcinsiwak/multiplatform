package pl.msiwak.multiplatform.shared.model

import kotlinx.serialization.Serializable

@Serializable
class ApiUpdateExerciseNameRequest(
    val exerciseId: String,
    val name: String
)
