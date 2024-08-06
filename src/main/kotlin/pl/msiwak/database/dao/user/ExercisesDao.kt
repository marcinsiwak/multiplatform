package pl.msiwak.database.dao.user

import pl.msiwak.entities.CategoryEntity

interface ExercisesDao {
    suspend fun addCategory(categoryEntity: CategoryEntity): CategoryEntity?
    suspend fun getCategory(categoryId: String, userId: String): CategoryEntity?
    suspend fun getCategoryByExercise(exerciseId: String): CategoryEntity?
    suspend fun getCategories(userId: String): List<CategoryEntity>
    suspend fun removeCategory(categoryId: String)
    suspend fun addExercise(categoryEntity: CategoryEntity)
    suspend fun addResult(categoryEntity: CategoryEntity)
}
