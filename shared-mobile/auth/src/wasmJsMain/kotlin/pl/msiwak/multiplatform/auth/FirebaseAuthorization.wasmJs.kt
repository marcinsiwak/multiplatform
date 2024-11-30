package pl.msiwak.multiplatform.auth

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.AuthResult
import pl.msiwak.multiplatform.commonObject.FirebaseUser

actual class FirebaseAuthorization {
    actual suspend fun createNewUser(email: String, password: String) {
    }

    actual suspend fun loginUser(
        email: String,
        password: String
    ): AuthResult {
        TODO("Not yet implemented")
    }

    actual suspend fun loginWithGoogle(
        googleToken: String?,
        accessToken: String?
    ): AuthResult {
        TODO("Not yet implemented")
    }

    actual fun observeAuthStateChanged(): Flow<FirebaseUser?> {
        TODO("Not yet implemented")
    }

    actual suspend fun logoutUser() {
    }

    actual suspend fun resendVerificationEmail() {
    }

}