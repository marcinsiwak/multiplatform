package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    val id: String,
    val email: String? = null,
    val username: String? = null
)
