package pl.msiwak.infrastructure.config.auth.firebase

import com.google.firebase.auth.FirebaseToken
import io.ktor.http.auth.HttpAuthHeader
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.AuthenticationFunction
import io.ktor.server.auth.AuthenticationProvider
import io.ktor.server.auth.parseAuthorizationHeader
import io.ktor.server.request.ApplicationRequest

private const val FIREBASE_IMPLEMENTATION_ERROR =
    "Firebase auth validate function is not specified, use firebase { validate { ... } } to fix this"

class FirebaseConfig(name: String?) : AuthenticationProvider.Config(name) {
    internal var authHeader: (ApplicationCall) -> HttpAuthHeader? =
        { call -> call.request.parseAuthorizationHeaderOrNull() }

    var firebaseAuthenticationFunction: AuthenticationFunction<FirebaseToken> = {
        throw NotImplementedError(FIREBASE_IMPLEMENTATION_ERROR)
    }

    fun validate(validate: suspend ApplicationCall.(FirebaseToken) -> FirebaseUser?) {
        firebaseAuthenticationFunction = validate
    }
}

fun ApplicationRequest.parseAuthorizationHeaderOrNull(): HttpAuthHeader? = try {
    parseAuthorizationHeader()
} catch (ex: IllegalArgumentException) {
    println("failed to parse token: $ex")
    null
}
