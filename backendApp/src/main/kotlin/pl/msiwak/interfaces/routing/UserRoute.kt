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
import pl.msiwak.infrastructure.config.auth.apikey.API_KEY_AUTH
import pl.msiwak.infrastructure.config.auth.firebase.FIREBASE_AUTH
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseUser
import pl.msiwak.interfaces.controller.UserController
import pl.msiwak.multiplatform.shared.common.Role
import pl.msiwak.multiplatform.shared.model.ApiDeviceToken
import pl.msiwak.multiplatform.shared.model.ApiUser

fun Route.addUserRoutes() {
    val userController by inject<UserController>()

    authenticate(API_KEY_AUTH) {
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
                    val principal =
                        principal<FirebaseUser>() ?: return@post call.respond(HttpStatusCode.Unauthorized)
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
                    val principal =
                        call.principal<FirebaseUser>() ?: return@get call.respond(HttpStatusCode.Unauthorized)
                    userController.getUser(principal.userId)?.let {
                        respond(status = HttpStatusCode.OK, message = it)
                    } ?: return@get call.respond(HttpStatusCode.NotFound)
                }
            }

            post("/user/notification") {
                with(call) {
                    val principal =
                        call.principal<FirebaseUser>() ?: return@post call.respond(HttpStatusCode.Unauthorized)
                    receive<ApiDeviceToken>().run {
                        userController.registerUserDeviceForNotification(token, principal.userId)
                        respond(HttpStatusCode.OK)
                    }
                }
            }

            get("/users") {
                with(call) {
                    adminAuth { _ ->
                        userController.getUsers().run {
                            respond(status = HttpStatusCode.OK, message = this)
                        }
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
