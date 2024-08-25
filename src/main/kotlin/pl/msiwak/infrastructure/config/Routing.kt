package pl.msiwak.infrastructure.config

import io.ktor.server.application.*
import io.ktor.server.routing.*
import pl.msiwak.interfaces.routing.addExerciseRoute
import pl.msiwak.interfaces.routing.addUserRoutes

fun Application.configureRouting() {
    routing {
        addUserRoutes()
        addExerciseRoute()
    }
}
