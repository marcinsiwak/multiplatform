package pl.msiwak.infrastructure.config.auth.apikey

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.AuthenticationContext
import io.ktor.server.auth.AuthenticationProvider
import io.ktor.server.response.respond
import pl.msiwak.multiplatform.shared.common.API_KEY_HEADER
import pl.msiwak.multiplatform.shared.common.API_KEY_NONCE_HEADER
import pl.msiwak.multiplatform.shared.common.API_KEY_TIMESTAMP_HEADER

const val API_KEY_AUTH = "API_KEY"

class ApiKeyAuthProvider(config: ApiKeyConfig) : AuthenticationProvider(config) {
    private val authFunction = config.apiKeyAuthenticationFunction

    override suspend fun onAuthenticate(context: AuthenticationContext) {
        val principal = verifyApiKey(context.call, authFunction)
        if (principal == null) {
            context.call.respond(HttpStatusCode.Unauthorized)
        }
    }

    private suspend fun verifyApiKey(
        call: ApplicationCall,
        authFunction: suspend ApplicationCall.(String, String, String) -> ApiKeyPrincipal?
    ): ApiKeyPrincipal? {
        val apiKey = call.request.headers[API_KEY_HEADER] ?: return null
        val nonce = call.request.headers[API_KEY_NONCE_HEADER] ?: return null
        val timestamp = call.request.headers[API_KEY_TIMESTAMP_HEADER] ?: return null
        return authFunction(call, apiKey, nonce, timestamp)
    }
}
