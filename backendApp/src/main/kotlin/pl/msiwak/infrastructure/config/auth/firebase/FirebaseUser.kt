package pl.msiwak.infrastructure.config.auth.firebase

import kotlinx.serialization.Serializable
import pl.msiwak.multiplatform.shared.common.Role

@Serializable
data class FirebaseUser(
    val userId: String = "",
    val displayName: String = "",
    val role: Role = Role.USER
)
