package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.dispatcher.Dispatchers
import pl.msiwak.multiplatform.network.service.CategoryService
import pl.msiwak.multiplatform.shared.model.ApiUpdateExerciseNameRequest

class CategoryRepositoryImpl(private val categoryService: CategoryService) : CategoryRepository {

    override suspend fun downloadCategories() {}

    override suspend fun downloadCategory(id: String) {}

    override suspend fun observeCategory(id: String): Flow<Category> = withContext(Dispatchers.IO) {
        return@withContext flowOf(categoryService.downloadCategory(id))
    }

    override suspend fun observeCategories(): Flow<List<Category>> = withContext(Dispatchers.IO) {
        return@withContext flowOf(categoryService.downloadCategories())
    }

    override suspend fun createCategory(name: String, exerciseType: ExerciseType) = withContext(Dispatchers.IO) {
        categoryService.createCategory(name = name, exerciseType = exerciseType)
    }

    override suspend fun removeCategory(categoryId: String) = withContext(Dispatchers.IO) {
        categoryService.removeCategory(categoryId)
    }

    override suspend fun downloadExercise(exerciseId: String) {}

    override suspend fun observeExercise(exerciseId: String): Flow<Exercise> = withContext(Dispatchers.IO) {
        return@withContext categoryService.downloadExercise(exerciseId)
    }

    override suspend fun addExercise(categoryId: String, name: String, exerciseType: ExerciseType): String =
        withContext(Dispatchers.IO) {
            return@withContext categoryService.addExercise(
                categoryId = categoryId,
                name = name,
                exerciseType = exerciseType
            ).first().id
        }

    override suspend fun updateExerciseName(exercise: Exercise) = withContext(Dispatchers.IO) {
        categoryService.updateExerciseName(
            ApiUpdateExerciseNameRequest(
                exerciseId = exercise.id,
                name = exercise.exerciseTitle
            )
        )
    }

    override suspend fun removeExercise(exercise: Exercise) = withContext(Dispatchers.IO) {
        categoryService.removeExercise(exercise.id)
    }

    override suspend fun addResult(exerciseId: String, result: String, amount: String, dateTime: LocalDateTime) {
        withContext(Dispatchers.IO) {
            categoryService.addResult(exerciseId, result, amount, dateTime)
        }
    }

    override suspend fun removeResult(id: String) = withContext(Dispatchers.IO) {
        categoryService.removeResult(id)
    }

    override suspend fun clearDatabase() {}

    override suspend fun checkIfSynchronizationIsPossible(): Boolean {
        return false
    }

    override suspend fun startInitialSynchronization() {}
}
