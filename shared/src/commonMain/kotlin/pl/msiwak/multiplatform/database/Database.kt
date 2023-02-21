package pl.msiwak.multiplatform.database

import pl.msiwak.multiplatform.cache.AppDatabase
import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.entity.SummaryEntity

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        SummaryAdapter = plmsiwakmultiplatformcache.Summary.Adapter(
            resultsAdapter = resultListAdapter,
            exerciseTypeAdapter = exerciseTypeAdapter
        )
    )
    private val dbQuery = database.appDatabaseQueries

    fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllSummaries()
        }
    }

    fun getSummary(id: Long): SummaryEntity {
        return dbQuery.selectFromSummary(id, ::mapSummary).executeAsOne()
    }

    fun getAllSummaries(): List<SummaryEntity> {
        return dbQuery.selectAllFromSummary(::mapSummary).executeAsList()
    }

    fun insertSummaries(summaries: List<SummaryEntity>) {
        dbQuery.transaction {
            summaries.forEach {
                insertSummary(it)
            }
        }
    }

    fun insertSummary(summaryEntity: SummaryEntity) {
        dbQuery.insertSummary(
            exerciseTitle = summaryEntity.exerciseTitle,
            results = summaryEntity.results,
            exerciseType = summaryEntity.exerciseType
        )
    }

    fun updateSummary(summaryEntity: SummaryEntity) {
        dbQuery.updateSummary(
            summaryEntity.id,
            summaryEntity.exerciseTitle,
            summaryEntity.results,
            summaryEntity.exerciseType
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
    ): SummaryEntity {
        return SummaryEntity(id, exerciseTitle, results, exerciseType)
    }
}