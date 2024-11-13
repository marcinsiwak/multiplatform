package pl.msiwak.infrastructure.config.auth.firebase

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureFirebaseAuth() {
    install(Authentication) {
        firebase {
            validate {
                FirebaseUser(it.uid, it.email.orEmpty())
            }
        }
    }
}