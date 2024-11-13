package pl.msiwak.interfaces.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import pl.msiwak.infrastructure.config.auth.firebase.FIREBASE_AUTH
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseUser
import pl.msiwak.interfaces.controller.UserController
import pl.msiwak.interfaces.dtos.UserDTO

fun Route.addUserRoutes() {
    val userController by inject<UserController>()

    authenticate(FIREBASE_AUTH) {
        post("/user") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@post call.respond(HttpStatusCode.Unauthorized)
                receive<UserDTO>().run {
                    userController.addUser(
                        username ?: principal.displayName,
                        principal.displayName,
                        principal.userId
                    )
                }
                respond(HttpStatusCode.OK)
            }
        }

        get("/user") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@get call.respond(HttpStatusCode.Unauthorized)
                userController.getUser(principal.userId)?.let {
                    respond(status = HttpStatusCode.OK, message = it)
                } ?: return@get call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
