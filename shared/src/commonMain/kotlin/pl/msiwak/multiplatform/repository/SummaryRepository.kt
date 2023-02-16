package pl.msiwak.multiplatform.repository

import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.database.Database

class SummaryRepository(private val database: Database) {

    fun clearSummaries() {
        database.clearDatabase()
    }
    fun insertSummary(summary: Summary) {
        database.insertSummary(summary)
    }
    fun insertSummaries(summaries: List<Summary>) {
        database.insertSummaries(summaries)
    }

    fun updateSummary(summary: Summary) {
        database.updateSummary(summary)
    }

    fun removeSummary(id: Long) {
        database.removeSummary(id)
    }

    fun getSummary(id: Long): Summary {
        return database.getSummary(id)
    }
    fun getSummaries(): List<Summary> {
        return database.getAllSummaries()
    }
}