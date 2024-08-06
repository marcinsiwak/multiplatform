package pl.msiwak.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import pl.msiwak.auth.firebase.FIREBASE_AUTH
import pl.msiwak.auth.firebase.FirebaseUser
import pl.msiwak.commands.AddUserCommand
import pl.msiwak.queries.GetUserQuery
import pl.msiwak.dtos.UserDTO

fun Route.addUserRoutes() {
    val addUserCommand by inject<AddUserCommand>()
    val getUserQuery by inject<GetUserQuery>()

    authenticate(FIREBASE_AUTH) {
        post("/user") {
            val firebaseUser: FirebaseUser = call.principal() ?: return@post call.respond(HttpStatusCode.Unauthorized)
            val user = call.receive<UserDTO>()
            addUserCommand.invoke(firebaseUser.userId, user.name, user.email)
            call.respond(HttpStatusCode.OK)
        }

        get("/user") {
            val firebaseUser: FirebaseUser = call.principal() ?: return@get call.respond(HttpStatusCode.Unauthorized)
            val user = getUserQuery.invoke(firebaseUser.userId)
            user?.let {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = it
                )
            } ?: run {
                call.respond(HttpStatusCode.NotFound)
            }

        }
    }
}
