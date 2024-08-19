package pl.msiwak.database.dao.exercise

import pl.msiwak.entities.CategoryEntity

interface ExercisesDao {
    suspend fun addCategory(categoryEntity: CategoryEntity): CategoryEntity?
    suspend fun getCategory(categoryId: String): CategoryEntity?
    suspend fun getCategoryByExercise(exerciseId: String): CategoryEntity?
    suspend fun getCategoryByResult(resultId: String): CategoryEntity?
    suspend fun getCategories(): List<CategoryEntity>
    suspend fun removeCategory(categoryId: String)
    suspend fun removeExercise(exerciseId: String)
    suspend fun removeResult(resultId: String)
    suspend fun addExercise(categoryEntity: CategoryEntity)
    suspend fun addResult(categoryEntity: CategoryEntity)
    suspend fun updateCategory(category: CategoryEntity)
}
