package pl.msiwak.multiplatform.database.dao

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.data.common.ExerciseShort
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.database.Database

class CategoriesDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun getCategories(): List<CategoryData> {
        return getAllCategoriesWithExercise()
    }

    fun getCategory(id: Long): CategoryData {
        return getCategoryWithExercise(id)
    }

    fun observeCategory(id: Long): Flow<CategoryData> {
        val category = dbQuery.selectCategoryWithExercise(id).executeAsList()
        if (category.isEmpty()) {
            return dbQuery.selectFromCategory(id, ::mapCategory).executeAsList().asFlow()
        }
        return category.groupBy {
            Triple(it.id, it.name, it.exerciseType)
        }.map { (category, rows) ->
            val exercises =
                rows.map { ExerciseShort(it.id_, it.exerciseTitle) }
            CategoryData(category.first, category.second, exercises, category.third)
        }.asFlow()

    }

    fun insertCategories(categories: List<CategoryData>) {
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

    fun updateCategory(categoryData: CategoryData) {
        with(categoryData) {
            dbQuery.updateCategory(
                id = id,
                name = name,
                exercises = exercises,
                exerciseType = exerciseType
            )
        }
    }

    fun insertCategory(categoryData: CategoryData) {
        with(categoryData) {
            dbQuery.insertCategory(
                id = id,
                name = name,
                exercises = exercises,
                exerciseType = exerciseType
            )
        }
    }

    private fun getCategoryWithExercise(id: Long): CategoryData {
        val category = dbQuery.selectCategoryWithExercise(id).executeAsList()
        if (category.isEmpty()) {
            return dbQuery.selectFromCategory(id, ::mapCategory).executeAsOne()
        }
        return category.groupBy {
            Triple(it.id, it.name, it.exerciseType)
        }.map { (category, rows) ->
            val exercises =
                rows.map { ExerciseShort(it.id_, it.exerciseTitle) }
            CategoryData(category.first, category.second, exercises, category.third)
        }.firstOrNull() ?: CategoryData()
    }

    private fun getAllCategoriesWithExercise(): List<CategoryData> {
        val category = dbQuery.selectAllCategoriesWithExercise().executeAsList()
        return category.groupBy {
            Triple(it.id, it.name, it.exerciseType)
        }.map { (category, rows) ->
            val exercises = rows.filter { category.first == it.categoryId }
                .map { ExerciseShort(it.id_, it.exerciseTitle) }
            CategoryData(category.first, category.second, exercises, category.third)
        }
    }

    private fun mapCategory(
        id: Long,
        name: String,
        exercises: List<ExerciseShort>,
        exerciseType: ExerciseType
    ): CategoryData {
        return CategoryData(id, name, exercises, exerciseType)
    }

}