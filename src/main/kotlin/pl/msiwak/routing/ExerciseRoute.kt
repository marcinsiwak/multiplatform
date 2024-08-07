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
            val category = call.receive<CategoryDTO>()
            addCategoryCommand.invoke(category.name, category.exerciseType)
            call.respond(HttpStatusCode.OK)
        }

        get("/categories") {
            val categories = getCategoriesQuery.invoke()
            println("OUTPUT: ${categories.map { it.exercises }}")
            call.respond(HttpStatusCode.OK, categories)
        }

        get("/category/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
            val category = getCategoryQuery.invoke(id) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(HttpStatusCode.OK, category)
        }

        post("/exercise") {
            val exercise = call.receive<ExerciseDTO>()
            addExerciseCommand.invoke(exercise.categoryId, exercise.name)
            call.respond(HttpStatusCode.OK)
        }

        get("/exercise/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
            getExerciseQuery.invoke(id)
            call.respond(HttpStatusCode.OK)
        }

        post("/result") {
            val result = call.receive<ResultDTO>()
            addResultCommand.invoke(
                exerciseId = result.exerciseId,
                resultEntity = ResultEntity(amount = result.amount, result = result.result, date = result.date)
            )
            call.respond(HttpStatusCode.OK)
        }

//        get("/result") {
//            call.principal<FirebaseUser>() ?: return@get call.respond(HttpStatusCode.Unauthorized)
//            addResultCommand.invoke(result.exerciseId, ResultEntity(amount = result.amount, result = result.result, date = result.date))
//            call.respond(HttpStatusCode.OK)
//        }
    }
}
