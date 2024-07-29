package pl.msiwak.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: String? = null,
    val email: String,
    val name: String
)
