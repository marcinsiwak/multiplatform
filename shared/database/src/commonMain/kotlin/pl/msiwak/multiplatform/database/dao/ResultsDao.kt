package pl.msiwak.multiplatform.database.dao

import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.database.Database

class ResultsDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun getAllResults(): List<ResultData> {
        return dbQuery.selectAllFromResult(::mapResult).executeAsList()
    }

    fun updateResults(results: List<ResultData>) {
        results.forEach {
            updateResult(it)
        }
    }

    fun updateResult(result: ResultData) {
        dbQuery.updateResult(
            id = result.id,
            exerciseID = result.exerciseId,
            result = result.result,
            amount = result.amount,
            date = result.date
        )
    }

    fun removeResult(id: String) {
        dbQuery.removeResult(id)
    }

    fun removeAllResult() {
        dbQuery.removeAllCategories()
    }

    private fun mapResult(
        id: String,
        exerciseId: String,
        result: String,
        amount: String,
        date: LocalDateTime,
    ): ResultData {
        return ResultData(id, exerciseId, result, amount, date)
    }
}