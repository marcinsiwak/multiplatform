package pl.msiwak.multiplatform.network.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.commonObject.ExerciseType
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.network.api.CategoryApi
import pl.msiwak.multiplatform.network.mapper.CategoryMapper
import pl.msiwak.multiplatform.network.mapper.ExerciseMapper
import pl.msiwak.multiplatform.network.mapper.ResultMapper
import pl.msiwak.multiplatform.shared.model.ApiCategory
import pl.msiwak.multiplatform.shared.model.ApiExercise
import pl.msiwak.multiplatform.shared.model.ApiResult
import pl.msiwak.multiplatform.shared.model.ApiSynchronizationRequest
import pl.msiwak.multiplatform.shared.model.ApiUpdateExerciseNameRequest

class CategoryService(
    private val categoryApi: CategoryApi,
    private val categoryMapper: CategoryMapper,
    private val exerciseMapper: ExerciseMapper,
    private val resultMapper: ResultMapper
) {

    suspend fun downloadCategories(): Flow<List<Category>> {
        return categoryApi.downloadCategories().map { categories ->
            categories.map {
                categoryMapper(it)
            }
        }
    }

    suspend fun downloadCategory(id: String): Flow<Category> {
        return categoryApi.downloadCategory(id).map { categoryMapper(it) }
    }

    suspend fun createCategory(name: String, exerciseType: ExerciseType) {
        categoryApi.createCategory(ApiCategory(name = name, exerciseType = exerciseType.name))
    }

    suspend fun removeCategory(id: String) {
        categoryApi.removeCategory(id)
    }

    suspend fun downloadExercise(id: String): Flow<Exercise> {
        return categoryApi.downloadExercise(id).map { exerciseMapper(it) }
    }

    suspend fun addExercise(categoryId: String, name: String, exerciseType: ExerciseType): Flow<Exercise> {
        return categoryApi.addExercise(
            ApiExercise(
                categoryId = categoryId,
                name = name,
                exerciseType = exerciseType.name
            )
        ).map { exerciseMapper(it) }
    }

    suspend fun updateExerciseName(updateExerciseNameRequest: ApiUpdateExerciseNameRequest) {
        return categoryApi.updateExerciseName(updateExerciseNameRequest)
    }

    suspend fun removeExercise(id: String) {
        categoryApi.removeExercise(id)
    }

    suspend fun addResult(
        exerciseId: String,
        result: String,
        amount: String,
        dateTime: LocalDateTime
    ): Flow<ResultData> {
        return categoryApi.addResult(
            ApiResult(
                exerciseId = exerciseId,
                result = result,
                amount = amount,
                date = dateTime.toInstant(TimeZone.UTC)
            )
        ).map { resultMapper(it) }
    }

    suspend fun removeResult(id: String) {
        categoryApi.removeResult(id)
    }

    suspend fun startInitialSynchronization(synchronizationRequest: ApiSynchronizationRequest) {
        categoryApi.startInitialSynchronization(synchronizationRequest)
    }
}
