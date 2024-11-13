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
import pl.msiwak.interfaces.controller.ExerciseController
import pl.msiwak.interfaces.dtos.CategoryDTO
import pl.msiwak.interfaces.dtos.ExerciseDTO
import pl.msiwak.interfaces.dtos.ResultDTO
import pl.msiwak.interfaces.dtos.SynchronizeDataDTO

fun Route.addExerciseRoute() {
    val exerciseController by inject<ExerciseController>()

    authenticate(FIREBASE_AUTH) {
        get("/categories") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@get respond(HttpStatusCode.Unauthorized)
                exerciseController.getCategories(principal.userId).run { respond(HttpStatusCode.OK, this) }
            }
        }

        post("/category") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@post respond(HttpStatusCode.Unauthorized)
                receive<CategoryDTO>().run {
                    respond(HttpStatusCode.OK, exerciseController.addCategory(name, exerciseType, principal.userId))
                }
            }
        }

        get("/category/{id}") {
            with(call) {
                val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                exerciseController.getCategory(id)?.let { respond(HttpStatusCode.OK, it) } ?: return@get call.respond(
                    HttpStatusCode.NotFound
                )
            }
        }

        delete("/category/{id}") {
            with(call) {
                val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
                exerciseController.removeCategory(id)
                respond(HttpStatusCode.OK)
            }
        }

        post("/exercise") {
            with(call) {
                receive<ExerciseDTO>().run {
                    exerciseController.addExercise(categoryId, name)?.let {
                        respond(HttpStatusCode.OK, it)
                    }
                }
            }
        }

        get("/exercise/{id}") {
            with(call) {
                val id = parameters["id"] ?: return@get respond(HttpStatusCode.BadRequest)
                exerciseController.getExercise(id)?.let { respond(HttpStatusCode.OK, it) }
                    ?: respond(HttpStatusCode.NotFound)
            }
        }

        delete("/exercise/{id}") {
            with(call) {
                val id = parameters["id"] ?: return@delete respond(HttpStatusCode.BadRequest)
                exerciseController.removeExercise(id)
                respond(HttpStatusCode.OK)
            }
        }

        post("/result") {
            with(call) {
                receive<ResultDTO>().run {
                    exerciseController.addResult(
                        exerciseId = exerciseId,
                        amount = amount,
                        result = result,
                        date = date
                    )?.let {
                        respond(HttpStatusCode.OK, it)
                    }
                }
            }
        }

        delete("/result/{id}") {
            with(call) {
                val id = parameters["id"] ?: return@delete respond(HttpStatusCode.BadRequest)
                exerciseController.removeResult(id)
                respond(HttpStatusCode.OK)
            }
        }

        post("/synchronize") {
            with(call) {
                val principal = principal<FirebaseUser>() ?: return@post respond(HttpStatusCode.Unauthorized)
                receive<SynchronizeDataDTO>().run {
                    exerciseController.synchronizeData(
                        categories,
                        exercises,
                        results,
                        principal.userId
                    )
                    respond(HttpStatusCode.OK, this)
                }
            }
        }
    }
}
