package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ResultData

interface CategoryRepository {
    suspend fun downloadCategories()
    suspend fun downloadCategory(id: String)
    suspend fun observeCategory(id: String): Flow<Category>
    suspend fun observeCategories(): Flow<List<Category>>
    suspend fun createCategory(category: Category)
    suspend fun removeCategory(categoryId: String)
    suspend fun downloadExercise(exerciseId: String)
    suspend fun observeExercise(exerciseId: String): Flow<Exercise>
    suspend fun addExercise(exercise: Exercise): String
    suspend fun updateExerciseName(exercise: Exercise)
    suspend fun removeExercise(exercise: Exercise)
    suspend fun addResult(result: ResultData)
    suspend fun removeResult(id: String)
    suspend fun clearDatabase()
    suspend fun checkIfSynchronizationIsPossible(): Boolean
    suspend fun startInitialSynchronization()
}
