package pl.msiwak.multiplatform.network.service

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.network.client.CategoryClient
import pl.msiwak.multiplatform.network.mapper.CategoryMapper
import pl.msiwak.multiplatform.network.model.ApiCategoryRequest
import pl.msiwak.multiplatform.network.model.ApiExerciseRequest
import pl.msiwak.multiplatform.network.model.ApiResultRequest

class CategoryService(
    private val categoryClient: CategoryClient,
    private val mapper: CategoryMapper
) {

    suspend fun downloadCategories(): List<Category> {
        return categoryClient.downloadCategories()
            .first()
            .map { category ->
                mapper(category)
            }
    }

    suspend fun downloadCategory(id: String): Category {
        return categoryClient.downloadCategory(id)
            .map { mapper(it) }
            .first()
    }

    suspend fun createCategory(category: ApiCategoryRequest) {
        categoryClient.createCategory(category)
    }

    suspend fun removeCategory(id: String) {
        categoryClient.removeCategory(id)
    }

    suspend fun downloadExercise(id: String) {
        categoryClient.downloadExercise(id)
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
