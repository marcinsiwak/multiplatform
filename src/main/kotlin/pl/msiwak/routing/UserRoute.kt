package pl.msiwak.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import pl.msiwak.auth.firebase.FIREBASE_AUTH
import pl.msiwak.auth.firebase.FirebaseUser
import pl.msiwak.commands.AddUserCommand

fun Route.addUserRoutes() {
    authenticate(FIREBASE_AUTH) {
        post("/addUser") {
            val firebaseUser: FirebaseUser = call.principal() ?: return@post call.respond(HttpStatusCode.Unauthorized)
            val formParameters = call.receiveParameters()
            val name = formParameters.getOrFail("name")
            val email = formParameters.getOrFail("email")

            call.respond("User is authenticated: $firebaseUser")
        }

        get("/getUser") {

        }
    }
}
