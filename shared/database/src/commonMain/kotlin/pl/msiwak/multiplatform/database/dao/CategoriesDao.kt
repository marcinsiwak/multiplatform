package pl.msiwak.multiplatform.database.dao

import app.cash.sqldelight.Query
import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.database.Database
import plmsiwakmultiplatformdatabasecache.CategoryDB

class CategoriesDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun observeCategories(): Flow<List<Category>> {
        return dbQuery.selectAllFromCategory()
            .asFlow()
            .combine(
                dbQuery.selectAllFromExercises(::mapExercise).asFlow()
            ) { categoryQuery: Query<CategoryDB>, exerciseQuery: Query<Exercise> ->

                val category = categoryQuery.executeAsList()
                val exercises = exerciseQuery.executeAsList()
                    .sortedByDescending { exercise -> exercise.creationDate }

                Pair(category, exercises)
            }
            .map { (categories, exercises) ->
                categories.map { category ->
                    Category(
                        id = category.id,
                        name = category.name,
                        exerciseType = category.exerciseType,
                        exercises = exercises.filter { it.categoryId == category.id },
                        creationDate = category.creationDate
                    )
                }.sortedByDescending { category -> category.creationDate }
            }
    }

    fun observeCategory(id: String): Flow<Category> {
        return dbQuery.selectFromCategory(id)
            .asFlow()
            .combine(
                dbQuery.selectFromExerciseByCategory(id, ::mapExercise).asFlow()
            ) { categoryQuery: Query<CategoryDB>, exerciseQuery: Query<Exercise> ->
                val category = categoryQuery.executeAsOne()
                val exercises = exerciseQuery.executeAsList()
                    .sortedByDescending { exercise -> exercise.creationDate }

                Pair(category, exercises)
            }.map { (category, exercises) ->
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