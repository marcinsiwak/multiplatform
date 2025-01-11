package pl.msiwak.infrastructure.config.auth.firebase

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import pl.msiwak.multiplatform.shared.common.Role

fun Application.configureFirebaseAuth() {
    install(Authentication) {
        firebase {
            validate {
                FirebaseUser(it.uid, it.email.orEmpty(), mapRole(it.claims))
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
