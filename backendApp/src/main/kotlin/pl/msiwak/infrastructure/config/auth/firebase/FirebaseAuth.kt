package pl.msiwak.infrastructure.config.auth.firebase

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication

fun Application.configureFirebaseAuth() {
    install(Authentication) {
        firebase {
            validate {
                FirebaseUser(it.uid, it.email.orEmpty())
            }
        }
    }
}
