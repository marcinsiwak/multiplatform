package pl.msiwak.interfaces.routing

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.RoutingCall
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject
import pl.msiwak.infrastructure.config.auth.firebase.FIREBASE_AUTH
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseUser
import pl.msiwak.interfaces.controller.UserController
import pl.msiwak.multiplatform.shared.common.Role
import pl.msiwak.multiplatform.shared.model.ApiUser

fun Route.addUserRoutes() {
    val userController by inject<UserController>()

    post("/user") {
        with(call) {
            receive<ApiUser>().run {
                userController.addUser(
                    id,
                    email,
                    email
                )
                respond(HttpStatusCode.OK)
            }
        }
    }

    authenticate(FIREBASE_AUTH) {
        post("/googleUser") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@post call.respond(HttpStatusCode.Unauthorized)
                userController.addUser(
                    principal.userId,
                    principal.displayName,
                    principal.displayName
                )
                respond(HttpStatusCode.OK)
            }
        }
//
//        put("/user") {
//            with(call) {
//                val principal = principal<FirebaseUser>() ?: return@put call.respond(HttpStatusCode.Unauthorized)
//                receive<ApiUser>().run {
//                    userController.updateUser(
//                        username ?: principal.displayName,
//                        principal.displayName,
//                        principal.userId
//                    )
//                }
//                respond(HttpStatusCode.OK)
//            }
//        }

        get("/user") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@get call.respond(HttpStatusCode.Unauthorized)
                userController.getUser(principal.userId)?.let {
                    respond(status = HttpStatusCode.OK, message = it)
                } ?: return@get call.respond(HttpStatusCode.NotFound)
            }
        }

        get("/users") {
            with(call) {
                adminAuth {
                    userController.getUsers().run {
                        respond(status = HttpStatusCode.OK, message = it)
                    }
                }
            }
        }
    }
}

private suspend fun RoutingCall.adminAuth(block: suspend (FirebaseUser) -> Unit) {
    val principal = principal<FirebaseUser>() ?: return respond(HttpStatusCode.Unauthorized)
    if (principal.role != Role.ADMIN) return respond(HttpStatusCode.Forbidden)
    block(principal)
}