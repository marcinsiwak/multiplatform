package pl.msiwak.multiplatform.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.data.common.ExerciseShort
import pl.msiwak.multiplatform.data.entity.CategoryData
import pl.msiwak.multiplatform.data.entity.SummaryData
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.SummaryDao

class SummaryRepository(
    private val summaryDao: SummaryDao,
    private val categoriesDao: CategoriesDao
) {

    suspend fun clearSummaries() = withContext(Dispatchers.Default) {
        summaryDao.clearDatabase()
    }

    suspend fun insertSummary(summaryData: SummaryData): Long = withContext(Dispatchers.Default) {
        val id = summaryDao.insertSummary(summaryData)
        val category = categoriesDao.getCategory(summaryData.categoryId)
        val newExercisesList = category.exercises.toMutableList()
        newExercisesList.add(ExerciseShort(id = id, name = summaryData.exerciseTitle))
        categoriesDao.updateCategory(category.copy(exercises = newExercisesList))
        return@withContext id
    }

    suspend fun insertSummaries(summaries: List<SummaryData>) = withContext(Dispatchers.Default) {
        summaryDao.insertSummaries(summaries)
    }

    suspend fun updateSummary(summaryData: SummaryData) = withContext(Dispatchers.Default) {
        summaryDao.updateSummary(summaryData)
    }

    suspend fun removeSummary(id: Long) = withContext(Dispatchers.Default) {
        summaryDao.removeSummary(id)
    }

    suspend fun getSummary(id: Long): SummaryData = withContext(Dispatchers.Default) {
        return@withContext summaryDao.getSummary(id)
    }

    suspend fun getSummaries(): List<SummaryData> = withContext(Dispatchers.Default) {
        return@withContext summaryDao.getAllSummaries()
    }
}