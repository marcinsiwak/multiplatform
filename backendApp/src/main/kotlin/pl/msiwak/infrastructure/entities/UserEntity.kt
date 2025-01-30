package pl.msiwak.infrastructure.entities

data class UserEntity(
    val id: String,
    val email: String,
    val name: String,
    val role: String,
    val deviceToken: String?
)
