package pl.msiwak.multiplatform.database.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.database.Database
import pl.msiwak.multiplatform.database.extension.asFlow

class CategoriesDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun observeCategories(): Flow<List<Category>> {
        return dbQuery.selectAllFromCategory().asFlow().map {
            it.executeAsList().map { category ->
                val exercises = dbQuery.selectFromExerciseByCategory(category.id, ::mapExercise).executeAsList()
                Category(
                    id = category.id,
                    name = category.name,
                    exerciseType = category.exerciseType,
                    exercises = exercises,
                    creationDate = category.creationDate
                )
            }.sortedByDescending { category -> category.creationDate }
        }
    }

    fun observeCategory(id: String): Flow<Category> {
        return dbQuery.selectFromCategory(id).asFlow().map {
            val category = it.executeAsOne()
            val exercises = dbQuery.selectFromExerciseByCategory(id, ::mapExercise).executeAsList()
                .sortedByDescending { exercise -> exercise.creationDate }
            Category(
                id = category.id,
                name = category.name,
                exerciseType = category.exerciseType,
                exercises = exercises,
                creationDate = category.creationDate
            )
        }
    }

    fun insertCategories(categories: List<Category>) {
        categories.forEach {
            with(it) {
                dbQuery.insertCategory(
                    id = id,
                    name = name,
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
                exerciseType = exerciseType,
                creationDate = creationDate
            )
        }
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
        exerciseType: ExerciseType,
        creationDate: LocalDateTime,
    ): Category {
        return Category(id, name, exerciseType, emptyList(), creationDate)
    }

    private fun mapExercise(
        id: String,
        categoryId: String,
        exerciseTitle: String,
        exerciseType: ExerciseType,
        creationDate: LocalDateTime,
    ): Exercise {
        return Exercise(id, categoryId, exerciseTitle, emptyList(), exerciseType, creationDate)
    }

}