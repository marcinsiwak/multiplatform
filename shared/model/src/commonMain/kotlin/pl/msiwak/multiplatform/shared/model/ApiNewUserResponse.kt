package pl.msiwak.multiplatform.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiNewUserResponse(
    val idToken: String,
    val email: String,
    val localId: String
)
