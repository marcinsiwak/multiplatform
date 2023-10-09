package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.commonObject.Category
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.network.model.ApiCategory
import pl.msiwak.multiplatform.network.service.CategoryService

class CategoryRepository(
    private val categoriesDao: CategoriesDao,
    private val categoryService: CategoryService
) {

    suspend fun downloadCategories(): List<Category> = withContext(Dispatchers.Default) {
        val categories = categoryService.downloadCategories()
        categoriesDao.updateCategories(categories)
        return@withContext categories
    }

    suspend fun getCategory(id: String): Category = withContext(Dispatchers.Default) {
        return@withContext categoriesDao.getCategory(id)
    }

    fun observeCategory(id: String): Flow<Category> {
        return categoriesDao.observeCategory(id)
    }

    fun observeCategories(): Flow<List<Category>> {
        return categoriesDao.observeCategories()
    }

    suspend fun insertCategories(categories: List<Category>) =
        withContext(Dispatchers.Default) {
            categoriesDao.insertCategories(categories)
        }

    suspend fun createCategory(category: Category) = withContext(Dispatchers.Default) {
        categoryService.createCategory(
            ApiCategory(
                name = category.name,
                exerciseType = category.exerciseType.name,
            )
        )
    }

    suspend fun updateCategory(category: Category) = withContext(Dispatchers.Default) {
        categoriesDao.updateCategory(category)
    }

    suspend fun removeCategory(categoryId: String) = withContext(Dispatchers.Default) {
        categoriesDao.removeCategory(categoryId)
    }

}
