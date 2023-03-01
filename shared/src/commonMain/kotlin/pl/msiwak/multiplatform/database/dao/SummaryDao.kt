package pl.msiwak.multiplatform.database.dao

import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.entity.SummaryData
import pl.msiwak.multiplatform.database.Database

class SummaryDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun clearDatabase() = dbQuery.transaction {
        dbQuery.removeAllSummaries()
    }

    fun getSummary(id: Long): SummaryData {
        return dbQuery.selectFromSummary(id, ::mapSummary).executeAsOne()
    }

    fun getAllSummaries(): List<SummaryData> {
        return dbQuery.selectAllFromSummary(::mapSummary).executeAsList()
    }

    fun insertSummaries(summaries: List<SummaryData>) {
        dbQuery.transaction {
            summaries.forEach {
                insert(it)
            }
        }
    }

    fun insertSummary(summaryData: SummaryData): Long {
        insert(summaryData)
        return getLastInsertedRowId()
    }

    private fun insert(summaryData: SummaryData) {
        dbQuery.insertSummary(
            categoryId = summaryData.categoryId,
            exerciseTitle = summaryData.exerciseTitle,
            results = summaryData.results,
            exerciseType = summaryData.exerciseType,
        )
    }

    fun updateSummary(summaryData: SummaryData) {
        dbQuery.updateSummary(
            summaryData.id,
            summaryData.categoryId,
            summaryData.exerciseTitle,
            summaryData.results,
            summaryData.exerciseType
        )
    }

    fun removeSummary(id: Long) {
        dbQuery.removeSummary(id)
    }

    private fun getLastInsertedRowId(): Long {
        return dbQuery.selectLastInsertedRowId().executeAsOne()
    }

    private fun mapSummary(
        id: Long,
        categoryId: Long,
        exerciseTitle: String,
        results: List<ResultData>,
        exerciseType: ExerciseType
    ): SummaryData {
        return SummaryData(id, categoryId, exerciseTitle, results, exerciseType)
    }
}