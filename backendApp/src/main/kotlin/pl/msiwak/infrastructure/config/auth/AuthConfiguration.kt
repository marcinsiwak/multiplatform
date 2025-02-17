package pl.msiwak.infrastructure.config.auth

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import pl.msiwak.infrastructure.config.auth.defaultAuth.ApiKeyPrincipal
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseUser
import pl.msiwak.multiplatform.shared.common.Role
import pl.msiwak.multiplatform.shared.security.prepareDynamicApiKey

fun Application.configureAuthorization() {
    val backendApiKey = environment.config.property("apiKey").getString()
    install(Authentication) {
        defaultAuth {
            validate { apiKey, nonce, timestamp ->
                if (prepareDynamicApiKey(backendApiKey, nonce, timestamp) == apiKey) {
                    ApiKeyPrincipal(apiKey, nonce, timestamp)
                } else {
                    null
                }
            }
        }
        loggedUserAuth {
            validate { apiKey, nonce, timestamp, firebaseToken ->
                if (prepareDynamicApiKey(backendApiKey, nonce, timestamp) == apiKey) {
                    FirebaseUser(
                        firebaseToken.uid,
                        firebaseToken.email.orEmpty(),
                        mapRole(firebaseToken.claims)
                    )
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
