package pl.msiwak.multiplatform.network.model

data class ApiUser(
    val id: String,
    val email: String,
    val emailVerified: Boolean,
    val username: String
)
