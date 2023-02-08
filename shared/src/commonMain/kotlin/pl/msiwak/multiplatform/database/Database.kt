package pl.msiwak.multiplatform.database

import pl.msiwak.multiplatform.cache.AppDatabase
import pl.msiwak.multiplatform.data.entity.Summary

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllSummaries()
        }
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

    private fun insertSummary(summary: Summary) {
        dbQuery.insertSummary(
            exerciseType = summary.exerciseType,
            result = summary.result
        )
    }

    private fun mapSummary(
        exerciseType: String,
        result: String
    ): Summary {
        return Summary(exerciseType, result)
    }
}