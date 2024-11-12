package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    val id: String,
    val email: String? = null,
    val emailVerified: Boolean? = null,
    val username: String? = null
)
