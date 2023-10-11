package pl.msiwak.multiplatform.database.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Instant
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.database.Database
import pl.msiwak.multiplatform.database.extension.asFlow

class CategoriesDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun observeCategories(): Flow<List<Category>> {
        return dbQuery.selectAllFromCategory(::mapCategory).asFlow().map {
            it.executeAsList().sortedByDescending { category -> category.creationDate }
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

    fun updateExercise(exercise: Exercise) {
        val newExercises = mutableListOf<Exercise>()
        val exercises = dbQuery.getCategoryExercises(exercise.categoryId).executeAsOne()
        if (exercises.isEmpty()) {
            dbQuery.updateExercise(listOf(exercise), exercise.categoryId)
            return
        }
        if (exercises.any { it.id == exercise.id }) {
            exercises.forEach {
                if (it.id == exercise.id) {
                    newExercises.add(exercise)
                } else {
                    newExercises.add(it)
                }
            }
        } else {
            newExercises.addAll(exercises)
            newExercises.add(exercise)
        }

        dbQuery.updateExercise(newExercises, exercise.categoryId)
    }

    fun removeCategory(categoryId: String) {
        dbQuery.removeCategory(categoryId)
    }

    fun removeAllCategories() {
        dbQuery.removeAllCategories()
    }

    private fun mapCategory(
        id: String,
        name: String,
        exercises: List<Exercise>,
        exerciseType: ExerciseType,
        creationDate: Instant
    ): Category {
        return Category(id, name, exerciseType, exercises, creationDate)
    }

}