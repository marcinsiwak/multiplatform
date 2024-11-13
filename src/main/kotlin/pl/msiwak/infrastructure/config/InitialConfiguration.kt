package pl.msiwak.infrastructure.config

import io.ktor.server.application.*
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseAdmin
import pl.msiwak.infrastructure.database.DatabaseFactory

fun Application.initialConfiguration() {
    FirebaseAdmin.init()
    DatabaseFactory.init(environment.config)
}