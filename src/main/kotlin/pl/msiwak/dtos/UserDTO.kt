package pl.msiwak.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val email: String,
    val name: String
)
