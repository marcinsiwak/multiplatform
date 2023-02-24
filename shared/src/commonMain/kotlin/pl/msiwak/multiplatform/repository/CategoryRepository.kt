package pl.msiwak.multiplatform.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.database.dao.CategoriesDao

class CategoryRepository(private val categoriesDao: CategoriesDao) {

    suspend fun getCategories(): List<CategoryData> = withContext(Dispatchers.Default) {
        return@withContext categoriesDao.getCategories()
    }

    suspend fun getCategory(id: Long): CategoryData = withContext(Dispatchers.Default) {
        return@withContext categoriesDao.getCategory(id)
    }

    suspend fun insertCategory(categories: List<CategoryData>) = withContext(Dispatchers.Default) {
        return@withContext categoriesDao.insertCategories(categories)
    }

    suspend fun updateCategory(category: CategoryData) = withContext(Dispatchers.Default) {
        return@withContext categoriesDao.updateCategory(category)
    }

}
