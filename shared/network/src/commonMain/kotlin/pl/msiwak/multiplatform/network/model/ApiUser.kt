package pl.msiwak.multiplatform.network.model

import kotlinx.serialization.SerialName

@Serializable
data class ApiUser(
    @SerialName("id") val id: String,
    @SerialName("email") val email: String,
    @SerialName("emailVerified") val emailVerified: Boolean,
    @SerialName("username") val username: String
)
