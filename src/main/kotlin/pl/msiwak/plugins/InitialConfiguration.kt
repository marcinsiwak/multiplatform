package pl.msiwak.plugins

import io.ktor.server.application.*
import pl.msiwak.auth.firebase.FirebaseAdmin
import pl.msiwak.database.DatabaseFactory

fun Application.initialConfiguration() {
    FirebaseAdmin.init()
    DatabaseFactory.init(environment.config)
}