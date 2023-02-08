package pl.msiwak.multiplatform.repository

import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.database.Database

class SummaryRepository(private val database: Database) {

    fun insertSummaries(summaries: List<Summary>) {
        database.insertSummaries(summaries)
    }

    fun getSummaries(): List<Summary> {
        return database.getAllSummaries()
    }
}