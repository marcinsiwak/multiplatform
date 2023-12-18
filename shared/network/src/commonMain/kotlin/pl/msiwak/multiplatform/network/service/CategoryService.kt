package pl.msiwak.multiplatform.network.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.network.client.CategoryClient
import pl.msiwak.multiplatform.network.mapper.CategoryMapper
import pl.msiwak.multiplatform.network.mapper.ExerciseMapper
import pl.msiwak.multiplatform.network.mapper.ResultMapper
import pl.msiwak.multiplatform.network.model.ApiCategoryRequest
import pl.msiwak.multiplatform.network.model.ApiExerciseRequest
import pl.msiwak.multiplatform.network.model.ApiResultRequest
import pl.msiwak.multiplatform.network.model.ApiSynchronizationRequest
import pl.msiwak.multiplatform.network.model.ApiUpdateExerciseNameRequest

class CategoryService(
    private val categoryClient: CategoryClient,
    private val categoryMapper: CategoryMapper,
    private val exerciseMapper: ExerciseMapper,
    private val resultMapper: ResultMapper
) {

    suspend fun downloadCategories(): Flow<List<Category>> {
        return categoryClient.downloadCategories()
            .map { category ->
                category.map {
                    categoryMapper(it)
                }
            }
    }

    suspend fun downloadCategory(id: String): Flow<Category> {
        return categoryClient.downloadCategory(id)
            .map { categoryMapper(it) }
    }

    suspend fun createCategory(category: ApiCategoryRequest) {
        categoryClient.createCategory(category)
    }

    suspend fun removeCategory(id: String) {
        categoryClient.removeCategory(id)
    }

    suspend fun downloadExercise(id: String): Flow<Exercise> {
        return categoryClient.downloadExercise(id)
            .map { exerciseMapper(it) }
    }

    suspend fun addExercise(exerciseRequest: ApiExerciseRequest): Flow<Exercise> {
        return categoryClient.addExercise(exerciseRequest)
            .map { exerciseMapper(it) }
    }

    suspend fun updateExerciseName(updateExerciseNameRequest: ApiUpdateExerciseNameRequest) {
        return categoryClient.updateExerciseName(updateExerciseNameRequest)
    }

    suspend fun removeExercise(id: String) {
        categoryClient.removeExercise(id)
    }

    suspend fun addResult(result: ApiResultRequest): Flow<ResultData> {
        return categoryClient.addResult(result)
            .map { resultMapper(it) }
    }

    suspend fun removeResult(id: String) {
        categoryClient.removeResult(id)
    }

    suspend fun startInitialSynchronization(synchronizationRequest: ApiSynchronizationRequest) {
        categoryClient.startInitialSynchronization(synchronizationRequest)
    }
}
