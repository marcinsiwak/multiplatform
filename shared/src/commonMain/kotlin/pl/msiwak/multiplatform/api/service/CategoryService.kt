package pl.msiwak.multiplatform.api.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.msiwak.multiplatform.api.client.CategoryClient
import pl.msiwak.multiplatform.api.data.user.ApiCategory
import pl.msiwak.multiplatform.data.common.Category
import pl.msiwak.multiplatform.mapper.CategoryMapper

class CategoryService(
    private val categoryClient: CategoryClient,
    private val mapper: CategoryMapper
) {

    suspend fun getCategories(): Flow<List<Category>> {
        return categoryClient.getCategories()
            .map {
                it.map { category ->
                    mapper(category)
                }
            }
    }

    suspend fun createCategory(category: ApiCategory) {
        categoryClient.createCategory(category)
    }
}
