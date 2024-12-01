package pl.msiwak.multiplatform.commonObject

data class FirebaseUser(
    val uid: String?,
    val email: String?,
    val displayName: String?,
    val isEmailVerified: Boolean = false,
    val token: String?
)