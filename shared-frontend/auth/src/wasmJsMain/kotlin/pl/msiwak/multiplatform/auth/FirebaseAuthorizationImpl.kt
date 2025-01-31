package pl.msiwak.multiplatform.auth

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import pl.msiwak.multiplatform.auth.Firebase.authStateChanged
import pl.msiwak.multiplatform.auth.Firebase.clearAuthStateListener
import pl.msiwak.multiplatform.auth.Firebase.createUser
import pl.msiwak.multiplatform.auth.Firebase.loginUserWithGoogle
import pl.msiwak.multiplatform.auth.Firebase.loginUserWithPassword
import pl.msiwak.multiplatform.auth.Firebase.resendEmail
import pl.msiwak.multiplatform.auth.Firebase.signUserOut
import pl.msiwak.multiplatform.commonObject.AuthResult
import pl.msiwak.multiplatform.commonObject.FirebaseUser

class FirebaseAuthorizationImpl : FirebaseAuthorization {

    override suspend fun createNewUser(email: String, password: String): AuthResult? {
        val response = createUser(email, password).await()
        return AuthResult(
            user = response.let {
                FirebaseUser(
                    uid = it.uid,
                    email = it.email,
                    displayName = it.displayName,
                    isEmailVerified = it.emailVerified,
                    token = it.accessToken
                )
            }
        )
    }

    override suspend fun loginUser(
        email: String,
        password: String
    ): AuthResult {
        val response = loginUserWithPassword(email, password).await()

        return AuthResult(
            user = response.let {
                FirebaseUser(
                    uid = it.uid,
                    email = it.email,
                    displayName = it.displayName,
                    isEmailVerified = it.emailVerified,
                    token = it.accessToken
                )
            }
        )
    }

    override suspend fun loginWithGoogle(
        googleToken: String?,
        accessToken: String?
    ): AuthResult {
        val response = googleToken?.let { loginUserWithGoogle(it).await() }
        return AuthResult(
            user = response?.let {
                FirebaseUser(
                    uid = it.uid,
                    email = it.email,
                    displayName = it.displayName,
                    isEmailVerified = it.emailVerified,
                    token = it.accessToken
                )
            }
        )
    }

    override fun observeAuthStateChanged(): Flow<FirebaseUser?> = callbackFlow {
        authStateChanged { appUser ->
            trySend(
                appUser?.let {
                    FirebaseUser(
                        uid = it.uid,
                        email = it.email,
                        displayName = it.displayName,
                        isEmailVerified = it.emailVerified,
                        token = it.accessToken
                    )
                }
            )

        }
        awaitClose { clearAuthStateListener() }
    }

    override suspend fun logoutUser() {
        signUserOut()
    }

    override suspend fun resendVerificationEmail() {
        // hack for strange problem with error handling
        callbackFlow {
            resendEmail(
                errorCallback = {
                    trySend(it)
                }
            )
            awaitClose()
        }.collect {
            throw Throwable(it)
        }
    }
}
