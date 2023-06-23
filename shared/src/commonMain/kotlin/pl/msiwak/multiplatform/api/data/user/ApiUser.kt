package pl.msiwak.multiplatform.api.data.user

import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    val id: String,
    val email: String,
    val emailVerified: Boolean,
    val username: String
)
