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
            exerciseType = summary.exerciseType,
            results = summary.results
        )
    }

    fun updateSummary(summary: Summary) {
        dbQuery.updateSummary(summary.id, summary.exerciseType, summary.results)
    }

    fun removeSummary(id: Long) {
        dbQuery.removeSummary(id)
    }

    private fun mapSummary(
        id: Long,
        exerciseType: String,
        results: List<ResultData>
    ): Summary {
        return Summary(id, exerciseType, results)
    }
}