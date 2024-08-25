package pl.msiwak.infrastructure.database.dao.exercise

import pl.msiwak.domain.entities.CategoryEntity

interface ExercisesDao {
    suspend fun getCategory(categoryId: String): CategoryEntity?
    suspend fun getCategoryByExercise(exerciseId: String): CategoryEntity?
    suspend fun getCategoryByResult(resultId: String): CategoryEntity?
    suspend fun getCategories(userId: String): List<CategoryEntity>
    suspend fun removeCategory(categoryId: String)
    suspend fun updateCategory(category: CategoryEntity)
    suspend fun updateExercises(category: CategoryEntity)
    suspend fun updateResults(category: CategoryEntity)
}
