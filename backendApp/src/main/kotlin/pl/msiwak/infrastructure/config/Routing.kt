package pl.msiwak.infrastructure.config

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.html.a
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.title
import pl.msiwak.interfaces.routing.addExerciseRoute
import pl.msiwak.interfaces.routing.addUserRoutes

fun Application.configureRouting() {
    routing {
        get {
            call.respondHtml(HttpStatusCode.OK) {
                head {
                    title { +"Welcome Page" }
                }
                body {
                    h1 { +"Welcome to Ktor" }
                    p { +"This is a basic welcome page built using Ktor." }
                    a(href = "https://ktor.io") { +"Learn more about Ktor" }
                }
            }
        }
        route("/api") {
            addUserRoutes()
            addExerciseRoute()
        }
    }
}
