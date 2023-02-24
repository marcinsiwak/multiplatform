package pl.msiwak.multiplatform.database.dao

import pl.msiwak.multiplatform.data.common.ExerciseShort
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.database.Database

class CategoriesDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun getCategories(): List<CategoryData> {
        return dbQuery.selectAllFromCategory(::mapCategory).executeAsList()
    }

    fun getCategory(id: Long): CategoryData {
        return dbQuery.selectFromCategory(id, ::mapCategory).executeAsOne()
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

    private fun mapCategory(
        id: Long,
        name: String,
        exercises: List<ExerciseShort>,
        exerciseType: ExerciseType
    ): CategoryData {
        return CategoryData(id, name, exercises, exerciseType)
    }

}