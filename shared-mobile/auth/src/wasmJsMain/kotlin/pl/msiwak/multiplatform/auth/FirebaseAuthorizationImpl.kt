package pl.msiwak.multiplatform.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.msiwak.multiplatform.commonObject.AuthResult
import pl.msiwak.multiplatform.commonObject.FirebaseUser
import pl.msiwak.multiplatform.network.FirebaseApi

class FirebaseAuthorizationImpl(private val firebaseApi: FirebaseApi) : FirebaseAuthorization {

    override suspend fun createNewUser(email: String, password: String) {
    }

    override suspend fun loginUser(
        email: String,
        password: String
    ): AuthResult {
        return AuthResult(
            user = firebaseApi.loginUserWithPassword(email, password).let {
                FirebaseUser(
                    uid = it.localId,
                    email = it.email,
                    displayName = it.displayName,
                    isEmailVerified = it.emailVerified ?: false,
                    token = it.idToken
                )
            }
        )
    }

    override suspend fun loginWithGoogle(
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
                    isEmailVerified = it.emailVerified ?: false,
                    token = it.idToken
                )
            }
        )
    }

    override fun observeAuthStateChanged(): Flow<FirebaseUser?> {
        return flow { FirebaseUser("14", "a@a.com", null, true, "fwoanfwn") } // todo
    }

    override suspend fun logoutUser() {
    }

    override suspend fun resendVerificationEmail() {
    }
}
