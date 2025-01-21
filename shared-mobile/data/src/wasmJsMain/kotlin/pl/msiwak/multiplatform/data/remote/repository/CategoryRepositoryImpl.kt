package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.network.service.CategoryService
import pl.msiwak.multiplatform.shared.model.ApiCategory
import pl.msiwak.multiplatform.shared.model.ApiExercise
import pl.msiwak.multiplatform.shared.model.ApiResult
import pl.msiwak.multiplatform.shared.model.ApiUpdateExerciseNameRequest

class CategoryRepositoryImpl(
    private val categoryService: CategoryService,
) : CategoryRepository {

    override suspend fun downloadCategories() {}

    override suspend fun downloadCategory(id: String) {}

    override suspend fun observeCategory(id: String): Flow<Category> = withContext(Dispatchers.IO) {
        return@withContext categoryService.downloadCategory(id)
    }

    override suspend fun observeCategories(): Flow<List<Category>> = withContext(Dispatchers.IO) {
        return@withContext categoryService.downloadCategories()
    }

    override suspend fun createCategory(category: Category) = withContext(Dispatchers.IO) {
        categoryService.createCategory(
            ApiCategory(
                name = category.name,
                exerciseType = category.exerciseType.name
            )
        )
    }

    override suspend fun removeCategory(categoryId: String) = withContext(Dispatchers.IO) {
        categoryService.removeCategory(categoryId)
    }

    override suspend fun downloadExercise(exerciseId: String) {}

    override suspend fun observeExercise(exerciseId: String): Flow<Exercise> = withContext(Dispatchers.IO) {
        return@withContext categoryService.downloadExercise(exerciseId)
    }

    override suspend fun addExercise(exercise: Exercise): String = withContext(Dispatchers.IO) {
        return@withContext categoryService.addExercise(
            ApiExercise(
                categoryId = exercise.categoryId,
                name = exercise.exerciseTitle,
                exerciseType = exercise.exerciseType.name
            )
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

    override suspend fun addResult(result: ResultData) {
        withContext(Dispatchers.IO) {
            categoryService.addResult(
                ApiResult(
                    exerciseId = result.exerciseId,
                    result = result.result,
                    amount = result.amount,
                    date = result.date.toInstant(TimeZone.currentSystemDefault())
                )
            )
        }
    }

    override suspend fun removeResult(id: String) = withContext(Dispatchers.IO) {
        categoryService.removeResult(id)
    }

    override suspend fun clearDatabase() {}

    override suspend fun checkIfSynchronizationIsPossible(): Boolean {
        return true
    }

    override suspend fun startInitialSynchronization() {}
}
