package pl.msiwak.infrastructure.config.auth.firebase

import com.google.firebase.auth.FirebaseToken
import io.ktor.http.auth.HttpAuthHeader
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.AuthenticationProvider
import io.ktor.server.auth.parseAuthorizationHeader
import io.ktor.server.request.ApplicationRequest

const val AUTH_IMPLEMENTATION_ERROR =
    "Auth validate function is not specified, use  { validate { ... } } to fix this"

class FirebaseConfig(name: String?) : AuthenticationProvider.Config(name) {
    internal var authHeader: (ApplicationCall) -> HttpAuthHeader? =
        { call -> call.request.parseAuthorizationHeaderOrNull() }

    var firebaseAuthFunction: suspend ApplicationCall.(String, String, String, FirebaseToken) -> FirebaseUser? =
        { _, _, _, _ ->
            throw NotImplementedError(AUTH_IMPLEMENTATION_ERROR)
        }

    fun validate(validate: suspend ApplicationCall.(String, String, String, FirebaseToken) -> FirebaseUser?) {
        firebaseAuthFunction = validate
    }
}

private fun ApplicationRequest.parseAuthorizationHeaderOrNull(): HttpAuthHeader? = try {
    parseAuthorizationHeader()
} catch (ex: IllegalArgumentException) {
    println("failed to parse token: $ex")
    null
}
