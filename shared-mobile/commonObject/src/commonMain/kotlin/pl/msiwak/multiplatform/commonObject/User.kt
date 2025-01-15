package pl.msiwak.multiplatform.commonObject

import pl.msiwak.multiplatform.shared.common.Role

data class User(
    val email: String,
    val userName: String,
    val role: Role
)
