package pl.msiwak.multiplatform.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.msiwak.multiplatform.commonObject.AuthResult
import pl.msiwak.multiplatform.commonObject.FirebaseUser
import pl.msiwak.multiplatform.network.FirebaseApi

actual class FirebaseAuthorization {

    private val firebaseApi = FirebaseApi()
    actual suspend fun createNewUser(email: String, password: String) {
    }

    actual suspend fun loginUser(
        email: String,
        password: String
    ): AuthResult {
        return AuthResult(null) // todo
    }

    actual suspend fun loginWithGoogle(
        googleToken: String?,
        accessToken: String?
    ): AuthResult {
        val response = googleToken?.let { firebaseApi.loginUserWithGoogle(it) }
        return AuthResult(
            user = response?.let {
                FirebaseUser(
                    uid = it.localId,
                    email = it.email,
                    displayName = it.displayName,
                    isEmailVerified = it.emailVerified,
                    token = it.idToken
                )
            }
        )
    }

    actual fun observeAuthStateChanged(): Flow<FirebaseUser?> {
        return flow { FirebaseUser("14", "a@a.com", null, true, "fwoanfwn") } // todo
    }

    actual suspend fun logoutUser() {
    }

    actual suspend fun resendVerificationEmail() {
    }
}
