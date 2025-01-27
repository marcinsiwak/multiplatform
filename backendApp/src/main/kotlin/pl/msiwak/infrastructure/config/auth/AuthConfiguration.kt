package pl.msiwak.infrastructure.config.auth

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import pl.msiwak.infrastructure.config.auth.apikey.ApiKeyPrincipal
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseUser
import pl.msiwak.multiplatform.shared.common.Role
import pl.msiwak.multiplatform.shared.security.prepareDynamicApiKey

fun Application.configureAuthorization() {
    val backendApiKey = environment.config.property("apiKey").getString()
    install(Authentication) {
        firebase {
            validate {
                FirebaseUser(
                    it.uid,
                    it.email.orEmpty(),
                    mapRole(it.claims)
                )
            }
        }
        apiKey {
            validate { apiKey, nonce, timestamp ->
                val hashedBackendApiKey = prepareDynamicApiKey(backendApiKey, nonce, timestamp)
                if (hashedBackendApiKey == apiKey) {
                    ApiKeyPrincipal(apiKey, nonce, timestamp)
                } else {
                    null
                }
            }
        }
    }
}

private fun mapRole(claims: MutableMap<String, Any>): Role {
    return when {
        claims["admin"] == true -> Role.ADMIN
        else -> Role.USER
    }
}
