package pl.msiwak

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import pl.msiwak.auth.firebase.FirebaseAdmin
import pl.msiwak.plugins.configureFirebaseAuth
import pl.msiwak.plugins.configureRouting

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        host = "0.0.0.0",
        module = Application::module,
        configure = {
            FirebaseAdmin.init()
        }
    ).start(wait = true)
}

fun Application.module() {
    configureFirebaseAuth()
    configureRouting()
}

