package pl.msiwak.multiplatform.auth

import pl.msiwak.multiplatform.auth.FirebaseErrorMessage.USER_ALREADY_EXISTS
import pl.msiwak.multiplatform.commonObject.exception.UserAlreadyExistsException
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

external interface ErrorResponse : JsAny {
    val message: String
}

@JsModule("./firebase/auth.js")
external object Firebase {
    fun createUser(email: String, password: String): Promise<AppUser>
    fun loginUserWithPassword(email: String, password: String): Promise<AppUser>
    fun loginUserWithGoogle(tokenId: String): Promise<AppUser>
    fun authStateChanged(callback: (AppUser?) -> Unit)
    fun signUserOut()
    fun resendEmail(errorCallback: (String?) -> Unit)
    fun clearAuthStateListener()
}

suspend fun <T : JsAny> Promise<T>.await(): T = suspendCoroutine { cont ->
    then(
        onFulfilled = { a ->
            cont.resume(a)
            a
        },
        onRejected = { js ->
            val errorResponse = js.unsafeCast<ErrorResponse>()
            cont.resumeWithException(parseError(errorResponse))
            errorResponse
        }
    )
}

private fun parseError(errorResponse: ErrorResponse): Exception {
    return when (val message = errorResponse.message) {
        USER_ALREADY_EXISTS -> UserAlreadyExistsException(message)
        else -> Exception(message)
    }
}

object FirebaseErrorMessage {
    const val USER_ALREADY_EXISTS = "Firebase: Error (auth/email-already-in-use)."
}
