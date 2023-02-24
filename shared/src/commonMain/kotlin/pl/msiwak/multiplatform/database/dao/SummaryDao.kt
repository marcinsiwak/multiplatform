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

    fun insertSummary(summaryData: SummaryData) {
        insert(summaryData)
    }

    private fun insert(summaryData: SummaryData) {
        dbQuery.insertSummary(
            exerciseTitle = summaryData.exerciseTitle,
            results = summaryData.results,
            exerciseType = summaryData.exerciseType,
        )
    }

    fun updateSummary(summaryData: SummaryData) {
        dbQuery.updateSummary(
            summaryData.id,
            summaryData.exerciseTitle,
            summaryData.results,
            summaryData.exerciseType
        )
    }

    fun removeSummary(id: Long) {
        dbQuery.removeSummary(id)
    }

    private fun mapSummary(
        id: Long,
        exerciseTitle: String,
        results: List<ResultData>,
        exerciseType: ExerciseType
    ): SummaryData {
        return SummaryData(id, exerciseTitle, results, exerciseType)
    }
}