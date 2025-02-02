package pl.msiwak.multiplatform.commonObject

import pl.msiwak.multiplatform.shared.common.Role

data class User(
    val id: String,
    val email: String,
    val userName: String,
    val role: Role,
    val deviceToken: String? = null
)
