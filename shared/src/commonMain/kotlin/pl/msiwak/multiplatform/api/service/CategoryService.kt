package pl.msiwak.multiplatform.api.service

import kotlinx.coroutines.flow.Flow
import pl.msiwak.multiplatform.api.client.CategoryClient
import pl.msiwak.multiplatform.api.data.user.ApiCategory

class CategoryService(private val categoryClient: CategoryClient) {

    suspend fun getCategories(): Flow<List<ApiCategory>> {
        return categoryClient.getCategories()
    }

    suspend fun createCategory(category: ApiCategory) {
        categoryClient.createCategory(category)
    }
}
