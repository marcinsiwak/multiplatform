package pl.msiwak.multiplatform.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.database.dao.CategoriesDao

class CategoryRepository(
    private val categoriesDao: CategoriesDao
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
            val out =categoriesDao.getCategories()
            out
        }
    }

    suspend fun getCategory(id: Long): CategoryData = withContext(Dispatchers.Default) {
        return@withContext categoriesDao.getCategory(id)
    }

    fun observeCategory(id: Long): Flow<CategoryData> {
        return categoriesDao.observeCategory(id).conflate()
    }

    fun observeCategories() : Flow<List<CategoryData>> {
        return categoriesDao.observeCategories()
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
