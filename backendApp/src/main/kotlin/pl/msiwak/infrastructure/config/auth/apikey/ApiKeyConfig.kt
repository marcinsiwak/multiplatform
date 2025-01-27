package pl.msiwak.infrastructure.config.auth.apikey

import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.AuthenticationProvider
import pl.msiwak.infrastructure.config.auth.firebase.AUTH_IMPLEMENTATION_ERROR

class ApiKeyConfig(name: String?) : AuthenticationProvider.Config(name) {
    var apiKeyAuthenticationFunction: suspend ApplicationCall.(String, String, String) -> ApiKeyPrincipal? =
        { _, _, _ ->
            throw NotImplementedError(AUTH_IMPLEMENTATION_ERROR)
        }

    fun validate(validate: suspend ApplicationCall.(String, String, String) -> ApiKeyPrincipal?) {
        apiKeyAuthenticationFunction = validate
    }
}
