package pl.msiwak.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pl.msiwak.routing.addUserRoutes
import pl.msiwak.routing.authenticatedRoute

fun Application.configureRouting() {
    routing {
        authenticatedRoute()
        addUserRoutes()
    }
}
