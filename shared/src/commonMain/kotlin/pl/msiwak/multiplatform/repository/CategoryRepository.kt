package pl.msiwak.multiplatform.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.ExerciseDao

class CategoryRepository(
    private val categoriesDao: CategoriesDao,
    private val exerciseDao: ExerciseDao
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
            categoriesDao.getCategories()
        }
    }

    suspend fun getCategory(id: Long): CategoryData = withContext(Dispatchers.Default) {
        return@withContext categoriesDao.getCategory(id)
    }

    suspend fun insertCategories(categories: List<CategoryData>) =
        withContext(Dispatchers.Default) {
            categoriesDao.insertCategories(categories)
        }

    suspend fun insertCategory(categories: CategoryData) = withContext(Dispatchers.Default) {
        categoriesDao.insertCategory(categories)
    }

    suspend fun updateCategory(category: CategoryData) = withContext(Dispatchers.Default) {
        categoriesDao.updateCategory(category)
    }

}
