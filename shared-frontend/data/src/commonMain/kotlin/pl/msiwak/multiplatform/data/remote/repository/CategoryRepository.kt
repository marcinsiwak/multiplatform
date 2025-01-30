package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType

interface CategoryRepository {
    suspend fun downloadCategories()
    suspend fun downloadCategory(id: String)
    suspend fun observeCategory(id: String): Flow<Category>
    suspend fun observeCategories(): Flow<List<Category>>
    suspend fun createCategory(name: String, exerciseType: ExerciseType)
    suspend fun removeCategory(categoryId: String)
    suspend fun downloadExercise(exerciseId: String)
    suspend fun observeExercise(exerciseId: String): Flow<Exercise>
    suspend fun addExercise(categoryId: String, name: String, exerciseType: ExerciseType): String
    suspend fun updateExerciseName(exercise: Exercise)
    suspend fun removeExercise(exercise: Exercise)
    suspend fun addResult(exerciseId: String, result: String, amount: String, dateTime: LocalDateTime)
    suspend fun removeResult(id: String)
    suspend fun clearDatabase()
    suspend fun checkIfSynchronizationIsPossible(): Boolean
    suspend fun startInitialSynchronization()
}
