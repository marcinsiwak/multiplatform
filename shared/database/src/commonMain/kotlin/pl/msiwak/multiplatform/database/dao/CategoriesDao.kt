package pl.msiwak.multiplatform.database.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.database.Database
import pl.msiwak.multiplatform.database.extension.asFlow

class CategoriesDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun observeCategories(): Flow<List<Category>> {
        return dbQuery.selectAllFromCategory(::mapCategory).asFlow().map {
            it.executeAsList().sortedByDescending { category ->
                category.creationDate
            }
        }
    }

    fun observeCategory(id: String): Flow<Category> {
        return dbQuery.selectFromCategory(id, ::mapCategory).asFlow().map {
            val category = it.executeAsOne()
            category.copy(exercises = category.exercises.sortedByDescending { exercise -> exercise.creationDate })
        }
    }

    fun insertCategories(categories: List<Category>) {
        categories.forEach {
            with(it) {
                dbQuery.insertCategory(
                    id = id,
                    name = name,
                    exercises = exercises,
                    exerciseType = exerciseType,
                    creationDate = creationDate
                )
            }
        }
    }

    fun updateCategories(categories: List<Category>) {
        categories.forEach {
            with(it) {
                dbQuery.updateCategory(
                    id = id,
                    name = name,
                    exercises = exercises,
                    exerciseType = exerciseType,
                    creationDate = creationDate
                )
            }
        }
    }

    fun updateCategory(category: Category) {
        with(category) {
            dbQuery.updateCategory(
                id = id,
                name = name,
                exercises = exercises,
                exerciseType = exerciseType,
                creationDate = creationDate
            )
        }
    }

    fun removeCategory(categoryId: String) {
        dbQuery.removeCategory(categoryId)
    }

    fun updateExercise(exercise: Exercise) {
//        dbQuery.updateExercise()
    }

    fun observeExercise(exerciseId: String): Flow<Exercise> {
        return flowOf()
    }

    fun removeExercise(exercise: Exercise) {
        val exercises = dbQuery.getCategoryExercises(exercise.categoryId).executeAsOne()
        val newExercises = exercises.filter { it.id != exercise.id }

        dbQuery.updateExercise(newExercises, exercise.categoryId)
    }

    fun removeAllCategories() {
        dbQuery.removeAllCategories()
    }

    private fun mapCategory(
        id: String,
        name: String,
        exercises: List<Exercise>,
        exerciseType: ExerciseType,
        creationDate: LocalDateTime
    ): Category {
        return Category(id, name, exerciseType, exercises, creationDate)
    }

}