package pl.msiwak.multiplatform.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.data.entity.SummaryData
import pl.msiwak.multiplatform.database.dao.SummaryDao

class SummaryRepository(private val summaryDao: SummaryDao) {

    suspend fun clearSummaries() = withContext(Dispatchers.Default) {
        summaryDao.clearDatabase()
    }

    suspend fun insertSummary(summaryData: SummaryData) = withContext(Dispatchers.Default) {
        summaryDao.insertSummary(summaryData)
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