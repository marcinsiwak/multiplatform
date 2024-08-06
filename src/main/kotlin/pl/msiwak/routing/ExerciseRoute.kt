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
            val firebaseUser: FirebaseUser = call.principal() ?: return@post call.respond(HttpStatusCode.Unauthorized)
            val category = call.receive<CategoryDTO>()
            addCategoryCommand.invoke(firebaseUser.userId, category.name, category.exerciseType)
            call.respond(HttpStatusCode.OK)
        }

        get("/categories") {
            val firebaseUser: FirebaseUser = call.principal() ?: return@get call.respond(HttpStatusCode.Unauthorized)
            val categories = getCategoriesQuery.invoke(firebaseUser.userId)
            println("OUTPUT: ${categories.map { it.exercises }}")
            call.respond(HttpStatusCode.OK, categories)
        }

        get("/category/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
            val firebaseUser: FirebaseUser = call.principal() ?: return@get call.respond(HttpStatusCode.Unauthorized)
            val category =
                getCategoryQuery.invoke(id, firebaseUser.userId) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(HttpStatusCode.OK, category)
        }

        post("/exercise") {
            val firebaseUser = call.principal<FirebaseUser>() ?: return@post call.respond(HttpStatusCode.Unauthorized)
            val exercise = call.receive<ExerciseDTO>()
            addExerciseCommand.invoke(exercise.categoryId, exercise.name, firebaseUser.userId)
            call.respond(HttpStatusCode.OK)
        }

        get("/exercise/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound)
            val firebaseUser = call.principal<FirebaseUser>() ?: return@get call.respond(HttpStatusCode.Unauthorized)
            getExerciseQuery.invoke(id, firebaseUser.userId)
            call.respond(HttpStatusCode.OK)
        }

        post("/result") {
            val firebaseUser = call.principal<FirebaseUser>() ?: return@post call.respond(HttpStatusCode.Unauthorized)
            val result = call.receive<ResultDTO>()
            addResultCommand.invoke(
                exerciseId = result.exerciseId,
                resultEntity = ResultEntity(amount = result.amount, result = result.result, date = result.date),
                userId = firebaseUser.userId
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
