package pl.msiwak.infrastructure.config.auth.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import io.ktor.http.HttpStatusCode
import io.ktor.http.auth.HttpAuthHeader
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.AuthenticationContext
import io.ktor.server.auth.AuthenticationFailedCause
import io.ktor.server.auth.AuthenticationProvider
import io.ktor.server.auth.UnauthorizedResponse
import io.ktor.server.response.respond
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.infrastructure.config.auth.defaultAuth.apiKeyHeadersHandler

const val FIREBASE_AUTH = "FIREBASE_AUTH"

@Suppress("LongParameterList")
class FirebaseAuthProvider(config: FirebaseConfig) : AuthenticationProvider(config) {
    private val authHeader: (ApplicationCall) -> HttpAuthHeader? = config.authHeader
    private val authFunction = config.firebaseAuthFunction

    override suspend fun onAuthenticate(context: AuthenticationContext) {
        val (apiKey, nonce, timestamp) = context.apiKeyHeadersHandler(context.call.request.headers) ?: return

        val token = authHeader(context.call)

        if (token == null) {
            context.challenge(
                FIREBASE_AUTH,
                AuthenticationFailedCause.InvalidCredentials
            ) { challengeFunc, call ->
                challengeFunc.complete()
                call.respond(UnauthorizedResponse(HttpAuthHeader.bearerAuthChallenge(realm = FIREBASE_AUTH)))
            }
            return
        }

        val principal = verifyFirebaseIdToken(context.call, token, apiKey, nonce, timestamp, authFunction)

        if (principal != null) {
            context.principal(principal)
        } else {
            context.call.respond(HttpStatusCode.Unauthorized)
        }
    }

    private suspend fun verifyFirebaseIdToken(
        call: ApplicationCall,
        authHeader: HttpAuthHeader,
        apiKey: String,
        nonce: String,
        timestamp: String,
        tokenData: suspend ApplicationCall.(String, String, String, FirebaseToken) -> FirebaseUser?
    ): FirebaseUser? {
        val token: FirebaseToken = if (authHeader.authScheme == "Bearer" && authHeader is HttpAuthHeader.Single) {
            withContext(Dispatchers.IO) {
                FirebaseAuth.getInstance().verifyIdToken(authHeader.blob)
            }
        } else {
            return null
        }
        return tokenData(call, apiKey, nonce, timestamp, token)
    }

    private fun HttpAuthHeader.Companion.bearerAuthChallenge(realm: String): HttpAuthHeader =
        HttpAuthHeader.Parameterized("Bearer", mapOf(HttpAuthHeader.Parameters.Realm to realm))
}
