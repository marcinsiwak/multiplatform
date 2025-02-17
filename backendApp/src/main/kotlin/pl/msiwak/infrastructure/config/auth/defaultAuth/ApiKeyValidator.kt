package pl.msiwak.infrastructure.config.auth.defaultAuth

import io.ktor.http.Headers
import io.ktor.server.auth.AuthenticationContext
import io.ktor.server.auth.AuthenticationFailedCause
import io.ktor.server.auth.UnauthorizedResponse
import io.ktor.server.response.respond
import pl.msiwak.multiplatform.shared.common.CustomHttpHeaders

fun AuthenticationContext.apiKeyHeadersHandler(headers: Headers): Triple<String, String, String>? {
    val apiKey = headers[CustomHttpHeaders.API_KEY_HEADER]
    val nonce = headers[CustomHttpHeaders.API_KEY_NONCE_HEADER]
    val timestamp = headers[CustomHttpHeaders.API_KEY_TIMESTAMP_HEADER]

    if (apiKey == null || nonce == null || timestamp == null) {
        challenge(
            DEFAULT_AUTH,
            AuthenticationFailedCause.InvalidCredentials
        ) { challengeFunc, call ->
            challengeFunc.complete()
            call.respond(message = UnauthorizedResponse())
        }
        return null
    }
    return Triple(apiKey, nonce, timestamp)
}
