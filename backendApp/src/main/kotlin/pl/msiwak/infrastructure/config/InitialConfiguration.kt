package pl.msiwak.infrastructure.config

import io.ktor.server.application.Application
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseAdmin
import pl.msiwak.infrastructure.database.DatabaseFactory

fun Application.initialConfiguration() {
    FirebaseAdmin.init(developmentMode)
    DatabaseFactory.init(environment.config)
}
