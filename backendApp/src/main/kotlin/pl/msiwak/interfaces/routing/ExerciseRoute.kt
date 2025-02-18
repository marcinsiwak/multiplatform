package pl.msiwak.interfaces.routing

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject
import pl.msiwak.infrastructure.config.auth.firebase.FIREBASE_AUTH
import pl.msiwak.infrastructure.config.auth.firebase.FirebaseUser
import pl.msiwak.interfaces.controller.ExerciseController
import pl.msiwak.multiplatform.shared.model.ApiCategory
import pl.msiwak.multiplatform.shared.model.ApiExercise
import pl.msiwak.multiplatform.shared.model.ApiResult
import pl.msiwak.multiplatform.shared.model.ApiSynchronizationRequest

@Suppress("LongMethod", "CyclomaticComplexMethod")
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
                receive<ApiCategory>().run {
                    respond(HttpStatusCode.OK, exerciseController.addCategory(name, exerciseType, principal.userId))
                }
            }
        }

        get("/category/{id}") {
            with(call) {
                val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                exerciseController.getCategory(id)?.let { respond(HttpStatusCode.OK, it) }
                    ?: return@get call.respond(
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
                receive<ApiExercise>().run {
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
                receive<ApiResult>().run {
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
                receive<ApiSynchronizationRequest>().run {
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
