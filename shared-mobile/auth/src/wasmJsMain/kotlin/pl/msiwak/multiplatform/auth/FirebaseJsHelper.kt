package pl.msiwak.multiplatform.auth

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

@JsModule("./firebase/auth.js")
external object Firebase {
    fun createUser(email: String, password: String): Promise<AppUser>
    fun loginUserWithPassword(email: String, password: String): Promise<AppUser>
    fun loginUserWithGoogle(tokenId: String): Promise<AppUser>
    fun authStateChanged(callback: (AppUser?) -> Unit)
    fun signUserOut()
    fun resendEmail()
}

suspend fun <T : JsAny> Promise<T>.await(): T = suspendCoroutine { cont ->
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
