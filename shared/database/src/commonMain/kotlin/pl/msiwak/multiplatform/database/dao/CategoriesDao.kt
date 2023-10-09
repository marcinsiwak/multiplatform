package pl.msiwak.multiplatform.database.dao

import app.cash.sqldelight.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.database.Database

class CategoriesDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

//    fun getCategories(): List<Category> {
//        return dbQuery.selectAllFromCategory(::mapCategory).executeAsList()
//    }

    fun getCategory(id: String): Category {
        return getCategoryWithExercise(id)
    }

    fun observeCategories(): Flow<List<Category>> {
        return dbQuery.selectAllCategoriesWithExercise().asFlow().map { query ->
            query.executeAsList().groupBy {
                Triple(it.id, it.name, it.exerciseType)
            }.map { (category, rows) ->
                val exercises = rows.filter { category.first == it.categoryId }
                    .map { Exercise(it.id_, it.exerciseTitle) }
                Category(category.first, category.second, category.third, exercises)
            }
        }.flatMapConcat {
            if (it.isEmpty()) {
                flowOf(dbQuery.selectAllFromCategory(::mapCategory).executeAsList())
            } else {
                flowOf(it)
            }
        }
    }

    fun observeCategory(id: String): Flow<Category> {
        return dbQuery.selectCategoryWithExercise(id).asFlow().map { query ->
            query.executeAsList().groupBy {
                Triple(it.id, it.name, it.exerciseType)
            }.map { (category, rows) ->
                val exercises =
                    rows.map { Exercise(it.id_, it.exerciseTitle) }
                Category(category.first, category.second, category.third, exercises)
            }.firstOrNull()
        }.flatMapConcat {
            if (it == null) {
                flowOf(dbQuery.selectFromCategory(id, ::mapCategory).executeAsOne())
            } else {
                flowOf(it)
            }
        }
    }

    fun insertCategories(categories: List<Category>) {
        categories.forEach {
            with(it) {
                dbQuery.insertCategory(
                    id = id,
                    name = name,
                    exercises = exercises,
                    exerciseType = exerciseType
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
                    exerciseType = exerciseType
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
                exerciseType = exerciseType
            )
        }
    }

    fun removeCategory(categoryId: String) {
        dbQuery.removeCategory(categoryId)
    }

    private fun getCategoryWithExercise(id: String): Category {
        val category = dbQuery.selectCategoryWithExercise(id).executeAsList()
        if (category.isEmpty()) {
            return dbQuery.selectFromCategory(id, ::mapCategory).executeAsOne()
        }
        return category.groupBy {
            Triple(it.id, it.name, it.exerciseType)
        }.map { (category, rows) ->
            val exercises =
                rows.map { Exercise(it.id_, it.exerciseTitle) }
            Category(category.first, category.second, category.third, exercises)
        }.firstOrNull() ?: Category()
    }

    private fun getAllCategoriesWithExercise(): List<Category> {
        val category = dbQuery.selectAllCategoriesWithExercise().executeAsList()
        return category.groupBy {
            Triple(it.id, it.name, it.exerciseType)
        }.map { (category, rows) ->
            val exercises = rows.filter { category.first == it.categoryId }
                .map { Exercise(it.id_, it.exerciseTitle) }
            Category(category.first, category.second, category.third, exercises)
        }
    }

    private fun mapCategory(
        id: String,
        name: String,
        exercises: List<Exercise>,
        exerciseType: ExerciseType
    ): Category {
        return Category(id, name, exerciseType, exercises)
    }

}