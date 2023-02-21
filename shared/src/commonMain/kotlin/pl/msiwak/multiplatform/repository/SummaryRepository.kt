package pl.msiwak.multiplatform.repository

import pl.msiwak.multiplatform.data.entity.SummaryEntity
import pl.msiwak.multiplatform.database.Database

class SummaryRepository(private val database: Database) {

    fun clearSummaries() {
        database.clearDatabase()
    }
    fun insertSummary(summaryEntity: SummaryEntity) {
        database.insertSummary(summaryEntity)
    }
    fun insertSummaries(summaries: List<SummaryEntity>) {
        database.insertSummaries(summaries)
    }

    fun updateSummary(summaryEntity: SummaryEntity) {
        database.updateSummary(summaryEntity)
    }

    fun removeSummary(id: Long) {
        database.removeSummary(id)
    }

    fun getSummary(id: Long): SummaryEntity {
        return database.getSummary(id)
    }
    fun getSummaries(): List<SummaryEntity> {
        return database.getAllSummaries()
    }
}