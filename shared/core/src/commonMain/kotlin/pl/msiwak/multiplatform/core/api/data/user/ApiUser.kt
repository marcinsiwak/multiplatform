package pl.msiwak.multiplatform.core.api.data.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiUser(
    @SerialName("id") val id: String,
    @SerialName("email") val email: String,
    @SerialName("emailVerified") val emailVerified: Boolean,
    @SerialName("username") val username: String
)
