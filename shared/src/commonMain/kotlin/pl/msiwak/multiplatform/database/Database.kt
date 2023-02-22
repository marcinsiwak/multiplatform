package pl.msiwak.multiplatform.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.cache.AppDatabase
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.entity.Summary

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        SummaryAdapter = plmsiwakmultiplatformcache.Summary.Adapter(
            resultsAdapter = resultListAdapter,
            exerciseTypeAdapter = exerciseTypeAdapter
        )
    )
    private val dbQuery = database.appDatabaseQueries

    suspend fun clearDatabase() = withContext(Dispatchers.Default){
        dbQuery.transaction {
            dbQuery.removeAllSummaries()
        }
    }

    suspend fun getSummary(id: Long): Summary = withContext(Dispatchers.Default) {
        return@withContext dbQuery.selectFromSummary(id, ::mapSummary).executeAsOne()
    }

    suspend fun getAllSummaries(): List<Summary> = withContext(Dispatchers.Default) {
        return@withContext dbQuery.selectAllFromSummary(::mapSummary).executeAsList()
    }

    suspend fun insertSummaries(summaries: List<Summary>) = withContext(Dispatchers.Default) {
        dbQuery.transaction {
            summaries.forEach {
                insert(it)
            }
        }
    }

    suspend fun insertSummary(summary: Summary) = withContext(Dispatchers.Default) {
        insert(summary)
    }

    private fun insert(summary: Summary) {
        dbQuery.insertSummary(
            exerciseTitle = summary.exerciseTitle,
            results = summary.results,
            exerciseType = summary.exerciseType
        )
    }

    suspend fun updateSummary(summary: Summary) = withContext(Dispatchers.Default){
        dbQuery.updateSummary(
            summary.id,
            summary.exerciseTitle,
            summary.results,
            summary.exerciseType
        )
    }

    suspend fun removeSummary(id: Long) = withContext(Dispatchers.Default){
        dbQuery.removeSummary(id)
    }

    private fun mapSummary(
        id: Long,
        exerciseTitle: String,
        results: List<ResultData>,
        exerciseType: ExerciseType
    ): Summary {
        return Summary(id, exerciseTitle, results, exerciseType)
    }
}