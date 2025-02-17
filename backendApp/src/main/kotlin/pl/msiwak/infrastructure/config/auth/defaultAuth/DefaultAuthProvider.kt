package pl.msiwak.infrastructure.config.auth.defaultAuth

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.AuthenticationContext
import io.ktor.server.auth.AuthenticationProvider
import io.ktor.server.response.respond

const val DEFAULT_AUTH = "DEFAULT_AUTH"

class DefaultAuthProvider(config: DefaultAuthConfig) : AuthenticationProvider(config) {
    private val authFunction = config.apiKeyAuthenticationFunction

    override suspend fun onAuthenticate(context: AuthenticationContext) {
        val (apiKey, nonce, timestamp) = context.apiKeyHeadersHandler(context.call.request.headers) ?: return

        val principal = verifyApiKey(context.call, apiKey, nonce, timestamp, authFunction)

        if (principal != null) {
            context.principal(principal)
        } else {
            context.call.respond(HttpStatusCode.Unauthorized)
        }
    }

    private suspend fun verifyApiKey(
        call: ApplicationCall,
        apiKey: String,
        nonce: String,
        timestamp: String,
        authFunction: suspend ApplicationCall.(String, String, String) -> ApiKeyPrincipal?
    ): ApiKeyPrincipal? {
        return authFunction(call, apiKey, nonce, timestamp)
    }
}
