package pl.msiwak.multiplatform.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.api.data.user.ApiCategory
import pl.msiwak.multiplatform.api.service.CategoryService
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.database.dao.CategoriesDao

class CategoryRepository(
    private val categoriesDao: CategoriesDao,
    private val categoryService: CategoryService
) {

    suspend fun getCategories(): List<CategoryData> = withContext(Dispatchers.Default) {
        val categories = categoriesDao.getCategories()
        return@withContext categories.ifEmpty {
            insertCategories(
                listOf(
                    CategoryData(
                        id = 1,
                        "Gym",
                        emptyList(),
                        ExerciseType.GYM
                    ), CategoryData(
                        id = 2,
                        "Running",
                        emptyList(),
                        ExerciseType.RUNNING
                    )
                )
            )
            val out = categoriesDao.getCategories()
            out
        }
    }

    suspend fun getCategory(id: Long): CategoryData = withContext(Dispatchers.Default) {
        return@withContext categoriesDao.getCategory(id)
    }

    fun observeCategory(id: Long): Flow<CategoryData> {
        return categoriesDao.observeCategory(id)
    }

    fun observeCategories(): Flow<List<CategoryData>> {
        return categoriesDao.observeCategories()
    }

    suspend fun insertCategories(categories: List<CategoryData>) =
        withContext(Dispatchers.Default) {
            categoriesDao.insertCategories(categories)
        }

    suspend fun createCategory(category: CategoryData) = withContext(Dispatchers.Default) {
        categoryService.createCategory(
            ApiCategory(
                name = category.name,
                exerciseType = category.exerciseType.name,
            )
        )
        categoriesDao.insertCategory(category)
    }

    suspend fun updateCategory(category: CategoryData) = withContext(Dispatchers.Default) {
        categoriesDao.updateCategory(category)
    }

    suspend fun removeCategory(categoryId: Long) = withContext(Dispatchers.Default) {
        categoriesDao.removeCategory(categoryId)
    }

}
