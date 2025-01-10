package pl.msiwak.multiplatform.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.msiwak.multiplatform.auth.Firebase.createUser
import pl.msiwak.multiplatform.commonObject.AuthResult
import pl.msiwak.multiplatform.commonObject.FirebaseUser
import pl.msiwak.multiplatform.network.FirebaseApi
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.js.Promise

external interface AppUser : JsAny {
    val uid: String
    val email: String
    val displayName: String?
    val emailVerified: Boolean
    val accessToken: String?
}

@JsModule("./firebase/index.js")
external object Firebase {
    fun createUser(email: String, password: String): Promise<AppUser>
}

suspend fun <T: JsAny> Promise<T>.await(): T = suspendCoroutine { cont ->
    then(
        onFulfilled = { a ->
            cont.resume(a)
            a
        },
        onRejected = { js ->
            cont.resumeWithException(Throwable())
            js
        }
    )
}

class FirebaseAuthorizationImpl(private val firebaseApi: FirebaseApi) : FirebaseAuthorization {

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
