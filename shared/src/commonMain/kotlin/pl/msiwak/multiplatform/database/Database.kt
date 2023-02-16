package pl.msiwak.multiplatform.database

import pl.msiwak.multiplatform.cache.AppDatabase
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.entity.Summary

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        SummaryAdapter = plmsiwakmultiplatformcache.Summary.Adapter(
            resultsAdapter = resultListAdapter
        )
    )
    private val dbQuery = database.appDatabaseQueries

    fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllSummaries()
        }
    }

    fun getSummary(id: Long): Summary {
        return dbQuery.selectFromSummary(id, ::mapSummary).executeAsOne()
    }

    fun getAllSummaries(): List<Summary> {
        return dbQuery.selectAllFromSummary(::mapSummary).executeAsList()
    }

    fun insertSummaries(summaries: List<Summary>) {
        dbQuery.transaction {
            summaries.forEach {
                insertSummary(it)
            }
        }
    }

    fun insertSummary(summary: Summary) {
        dbQuery.insertSummary(
            exerciseTitle = summary.exerciseTitle,
            results = summary.results
        )
    }

    fun updateSummary(summary: Summary) {
        dbQuery.updateSummary(summary.id, summary.exerciseTitle, summary.results)
    }

    fun removeSummary(id: Long) {
        dbQuery.removeSummary(id)
    }

    private fun mapSummary(
        id: Long,
        exerciseTitle: String,
        results: List<ResultData>
    ): Summary {
        return Summary(id, exerciseTitle, results)
    }
}