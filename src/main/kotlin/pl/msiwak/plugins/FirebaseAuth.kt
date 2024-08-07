package pl.msiwak.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import pl.msiwak.auth.firebase.FirebaseUser
import pl.msiwak.auth.firebase.firebase

fun Application.configureFirebaseAuth() {
    install(Authentication) {
        firebase {
            validate {
                FirebaseUser(it.uid, it.email.orEmpty())
            }
        }
    }
}