package pl.msiwak.multiplatform.shared.model

import kotlinx.serialization.Serializable
import pl.msiwak.multiplatform.shared.common.Role

@Serializable
data class ApiUser(
    val id: String? = null,
    val email: String? = null,
    val username: String? = null,
    val role: Role? = null
)
