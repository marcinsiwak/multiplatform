package pl.msiwak.multiplatform.repository

import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.database.Database

class SummaryRepository(private val database: Database) {

    suspend fun clearSummaries() {
        database.clearDatabase()
    }
    suspend fun insertSummary(summary: Summary) {
        database.insertSummary(summary)
    }
    suspend fun insertSummaries(summaries: List<Summary>) {
        database.insertSummaries(summaries)
    }

    suspend fun updateSummary(summary: Summary) {
        database.updateSummary(summary)
    }

    suspend fun removeSummary(id: Long) {
        database.removeSummary(id)
    }

    suspend fun getSummary(id: Long): Summary {
        return database.getSummary(id)
    }
    suspend fun getSummaries(): List<Summary> {
        return database.getAllSummaries()
    }
}