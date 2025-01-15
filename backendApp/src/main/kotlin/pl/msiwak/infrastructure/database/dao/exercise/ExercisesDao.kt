package pl.msiwak.infrastructure.database.dao.exercise

import pl.msiwak.infrastructure.entities.CategoryEntity
import pl.msiwak.infrastructure.entities.ExerciseEntity
import pl.msiwak.infrastructure.entities.ResultEntity

@Suppress("TooManyFunctions")
interface ExercisesDao {
    suspend fun getCategory(categoryId: String): CategoryEntity?
    suspend fun getCategoryByExercise(exerciseId: String): CategoryEntity?
    suspend fun getCategoryByResult(resultId: String): CategoryEntity?
    suspend fun getCategories(userId: String): List<CategoryEntity>
    suspend fun removeCategory(categoryId: String)
    suspend fun updateCategory(category: CategoryEntity)
    suspend fun updateExercises(category: CategoryEntity)
    suspend fun updateResults(category: CategoryEntity)

    suspend fun syncCategories(categories: List<CategoryEntity>)
    suspend fun syncExercises(exercises: List<ExerciseEntity>)
    suspend fun syncResults(results: List<ResultEntity>)
}
