package pl.msiwak.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.java.KoinJavaComponent.inject
import org.koin.ktor.ext.inject
import pl.msiwak.auth.firebase.FIREBASE_AUTH
import pl.msiwak.auth.firebase.FirebaseUser
import pl.msiwak.commands.AddUserCommand
import pl.msiwak.dtos.UserDTO

fun Route.addUserRoutes() {
    val addUserCommand by inject<AddUserCommand>()

    authenticate(FIREBASE_AUTH) {
        post("/addUser") {
            val firebaseUser: FirebaseUser = call.principal() ?: return@post call.respond(HttpStatusCode.Unauthorized)
            println("here1")
            val user = call.receive<UserDTO>()
            println("here2")
            addUserCommand.invoke(firebaseUser.userId, user.name, user.email)
            call.respond(HttpStatusCode.Accepted)
        }

        get("/getUser") {

        }
    }
}
