package pl.msiwak.multiplatform.database.dao

import pl.msiwak.multiplatform.commonObject.ResultData
import pl.msiwak.multiplatform.database.Database

class ResultsDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun updateResults(results: List<ResultData>) {
        results.forEach {
            updateResult(it)
        }
    }

    fun updateResult(result: ResultData) {
        dbQuery.updateResult(
            id = result.id,
            exerciseID = result.exerciseId,
            result = result.result.toString(),
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
}