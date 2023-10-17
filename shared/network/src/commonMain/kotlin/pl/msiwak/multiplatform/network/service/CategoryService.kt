package pl.msiwak.multiplatform.network.service

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.commonObject.Exercise
import pl.msiwak.multiplatform.network.client.CategoryClient
import pl.msiwak.multiplatform.network.mapper.CategoryMapper
import pl.msiwak.multiplatform.network.mapper.ExerciseMapper
import pl.msiwak.multiplatform.network.model.ApiCategoryRequest
import pl.msiwak.multiplatform.network.model.ApiExerciseRequest
import pl.msiwak.multiplatform.network.model.ApiResultRequest

class CategoryService(
    private val categoryClient: CategoryClient,
    private val categoryMapper: CategoryMapper,
    private val exerciseMapper: ExerciseMapper
) {

    suspend fun downloadCategories(): List<Category> {
        return categoryClient.downloadCategories()
            .first()
            .map { category ->
                categoryMapper(category)
            }
    }

    suspend fun downloadCategory(id: String): Category {
        return categoryClient.downloadCategory(id)
            .map { categoryMapper(it) }
            .first()
    }

    suspend fun createCategory(category: ApiCategoryRequest) {
        categoryClient.createCategory(category)
    }

    suspend fun removeCategory(id: String) {
        categoryClient.removeCategory(id)
    }

    suspend fun downloadExercise(id: String): Exercise {
        return categoryClient.downloadExercise(id)
            .map { exerciseMapper(it) }
            .first()
    }

    suspend fun addExercise(exerciseRequest: ApiExerciseRequest) {
        categoryClient.addExercise(exerciseRequest)
    }

    suspend fun removeExercise(id: String) {
        categoryClient.removeExercise(id)
    }

    suspend fun addResult(result: ApiResultRequest) {
        categoryClient.addResult(result)
    }
}
