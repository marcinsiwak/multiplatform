package pl.msiwak.multiplatform.core.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.core.api.data.user.ApiCategory
import pl.msiwak.multiplatform.core.api.service.CategoryService
import pl.msiwak.multiplatform.core.data.common.Category
import pl.msiwak.multiplatform.core.database.dao.CategoriesDao

class CategoryRepository(
    private val categoriesDao: CategoriesDao,
    private val categoryService: CategoryService
) {

    suspend fun downloadCategories(): List<Category> = withContext(Dispatchers.Default) {
        val categories = categoryService.downloadCategories()
        categoriesDao.insertCategories(categories)
        return@withContext categories
//        categories.ifEmpty {
//            insertCategories(
//                listOf(
//                    CategoryData(
//                        id = 1,
//                        "Gym",
//                        emptyList(),
//                        ExerciseType.GYM
//                    ), CategoryData(
//                        id = 2,
//                        "Running",
//                        emptyList(),
//                        ExerciseType.RUNNING
//                    )
//                )
//            )
//            val out = categoriesDao.getCategories()
//            out
//        }
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
