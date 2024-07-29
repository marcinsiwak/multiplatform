package pl.msiwak.database.dao.user

import pl.msiwak.entities.CategoryEntity

interface ExercisesDao {
    suspend fun addCategory(
        categoryUuid: String,
        userId: String,
        name: String,
        exerciseType: String
    ): CategoryEntity?

    suspend fun getCategory(categoryId: String): CategoryEntity?
    suspend fun removeCategory(categoryId: String)
}
