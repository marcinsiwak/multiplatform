package pl.msiwak.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import pl.msiwak.routing.addExerciseRoute
import pl.msiwak.routing.addUserRoutes

fun Application.configureRouting() {
    routing {
        addUserRoutes()
        addExerciseRoute()
    }
}
