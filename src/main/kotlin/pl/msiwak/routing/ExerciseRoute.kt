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
import pl.msiwak.dtos.CategoryDTO
import pl.msiwak.dtos.ExerciseDTO
import pl.msiwak.queries.GetCategoriesQuery
import pl.msiwak.queries.GetCategoryQuery

fun Route.addExerciseRoute() {
    val addCategoryCommand by inject<AddCategoryCommand>()
    val addExerciseCommand by inject<AddExerciseCommand>()
    val getCategoriesQuery by inject<GetCategoriesQuery>()
    val getCategoryQuery by inject<GetCategoryQuery>()

    authenticate(FIREBASE_AUTH) {
        post("/addCategory") {
            val firebaseUser: FirebaseUser = call.principal() ?: return@post call.respond(HttpStatusCode.Unauthorized)
            val category = call.receive<CategoryDTO>()
            addCategoryCommand.invoke(firebaseUser.userId, category.name, category.exerciseType)
            call.respond(HttpStatusCode.OK)
        }

        get("/getCategories") {
            val firebaseUser: FirebaseUser = call.principal() ?: return@get call.respond(HttpStatusCode.Unauthorized)
            val categories = getCategoriesQuery.invoke(firebaseUser.userId)
            println("OUTPUT: ${categories.map { it.exercises }}")
            call.respond(HttpStatusCode.OK, categories)
        }

        get("/getCategory") {
            val firebaseUser: FirebaseUser = call.principal() ?: return@get call.respond(HttpStatusCode.Unauthorized)
            val category = getCategoryQuery.invoke(firebaseUser.userId) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(HttpStatusCode.OK, category)
        }

        get("/addExercise") {
            val firebaseUser: FirebaseUser = call.principal() ?: return@get call.respond(HttpStatusCode.Unauthorized)
            val exercise = call.receive<ExerciseDTO>()
            addExerciseCommand.invoke(exercise.categoryId, exercise.name)
            call.respond(HttpStatusCode.OK)
        }
    }
}
