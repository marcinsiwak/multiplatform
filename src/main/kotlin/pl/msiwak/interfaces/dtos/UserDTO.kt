package pl.msiwak.interfaces.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val id: String? = null,
    val email: String? = null,
    val name: String? = null
)
