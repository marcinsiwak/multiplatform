package pl.msiwak.infrastructure.config.auth.firebase

import pl.msiwak.multiplatform.shared.common.Role

data class FirebaseUser(
    val userId: String = "",
    val displayName: String = "",
    val role: Role = Role.USER
)
