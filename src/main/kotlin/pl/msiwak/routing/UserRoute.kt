package pl.msiwak.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import pl.msiwak.auth.firebase.FIREBASE_AUTH
import pl.msiwak.commands.AddUserCommand
import pl.msiwak.dtos.UserDTO
import pl.msiwak.queries.GetUserQuery

fun Route.addUserRoutes() {
    val addUserCommand by inject<AddUserCommand>()
    val getUserQuery by inject<GetUserQuery>()

    authenticate(FIREBASE_AUTH) {
        post("/user") {
            with(call) {
                receive<UserDTO>().run { addUserCommand.invoke(name, email) }
                respond(HttpStatusCode.OK)
            }
        }

        get("/user") {
            with(call) {
                getUserQuery.invoke()?.let {
                    respond(status = HttpStatusCode.OK, message = it)
                } ?: return@get call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
