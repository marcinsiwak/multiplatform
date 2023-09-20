package pl.msiwak.multiplatform.network.service

import kotlinx.coroutines.flow.first
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.network.client.CategoryClient
import pl.msiwak.multiplatform.network.mapper.CategoryMapper
import pl.msiwak.multiplatform.network.model.ApiCategory

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

    suspend fun createCategory(category: ApiCategory) {
        categoryClient.createCategory(category)
    }
}
