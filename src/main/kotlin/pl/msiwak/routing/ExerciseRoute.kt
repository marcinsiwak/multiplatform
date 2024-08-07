package pl.msiwak.routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import pl.msiwak.auth.firebase.FIREBASE_AUTH
import pl.msiwak.commands.AddCategoryCommand
import pl.msiwak.commands.AddExerciseCommand
import pl.msiwak.commands.AddResultCommand
import pl.msiwak.dtos.CategoryDTO
import pl.msiwak.dtos.ExerciseDTO
import pl.msiwak.dtos.ResultDTO
import pl.msiwak.entities.ResultEntity
import pl.msiwak.queries.GetCategoriesQuery
import pl.msiwak.queries.GetCategoryQuery
import pl.msiwak.queries.GetExerciseQuery

fun Route.addExerciseRoute() {
    val addCategoryCommand by inject<AddCategoryCommand>()
    val addExerciseCommand by inject<AddExerciseCommand>()
    val addResultCommand by inject<AddResultCommand>()
    val getCategoriesQuery by inject<GetCategoriesQuery>()
    val getCategoryQuery by inject<GetCategoryQuery>()
    val getExerciseQuery by inject<GetExerciseQuery>()

    authenticate(FIREBASE_AUTH) {
        post("/category") {
            with(call) {
                receive<CategoryDTO>().run { addCategoryCommand.invoke(name, exerciseType) }
                respond(HttpStatusCode.OK)
            }
        }

        get("/categories") {
            getCategoriesQuery.invoke().run { call.respond(HttpStatusCode.OK, this) }
        }

        get("/category/{id}") {
            with(call) {
                val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                getCategoryQuery.invoke(id)?.let { respond(HttpStatusCode.OK, it) } ?: return@get call.respond(
                    HttpStatusCode.NotFound
                )
            }
        }

        post("/exercise") {
            with(call) {
                receive<ExerciseDTO>().run { addExerciseCommand.invoke(categoryId, name) }
                respond(HttpStatusCode.OK)
            }
        }

        get("/exercise/{id}") {
            with(call) {
                val id = parameters["id"] ?: return@get respond(HttpStatusCode.BadRequest)
                getExerciseQuery.invoke(id)?.let { respond(HttpStatusCode.OK, it) } ?: respond(HttpStatusCode.NotFound)
            }
        }

        post("/result") {
            with(call) {
                receive<ResultDTO>().run {
                    addResultCommand.invoke(
                        exerciseId = exerciseId,
                        resultEntity = ResultEntity(amount = amount, result = result, date = date)
                    )
                }
                respond(HttpStatusCode.OK)
            }
        }
    }
}
