package pl.msiwak.multiplatform.database.dao

import pl.msiwak.multiplatform.data.common.ExerciseType
import pl.msiwak.multiplatform.data.common.ResultData
import pl.msiwak.multiplatform.data.entity.ExerciseData
import pl.msiwak.multiplatform.database.Database

class ExerciseDao(database: Database) {

    private val dbQuery = database.getDatabaseQueries()

    fun clearDatabase() = dbQuery.transaction {
        dbQuery.removeAllExercises()
    }

    fun getExercise(id: Long): ExerciseData? {
        return dbQuery.selectFromExercise(id, ::mapExercise).executeAsOneOrNull()
    }

    fun getAllExercises(): List<ExerciseData> {
        return dbQuery.selectAllFromExercise(::mapExercise).executeAsList()
    }

    fun insertExercises(Exercises: List<ExerciseData>) {
        dbQuery.transaction {
            Exercises.forEach {
                insert(it)
            }
        }
    }

    fun insertExercise(exerciseData: ExerciseData): Long  {
        return dbQuery.transactionWithResult {
            insert(exerciseData)
            getLastInsertedRowId()
        }
    }

    private fun insert(exerciseData: ExerciseData) {
        dbQuery.insertExercise(
            categoryId = exerciseData.categoryId,
            exerciseTitle = exerciseData.exerciseTitle,
            results = exerciseData.results,
            exerciseType = exerciseData.exerciseType,
        )
    }

    fun updateExercise(exerciseData: ExerciseData) {
        dbQuery.updateExercise(
            exerciseData.id,
            exerciseData.categoryId,
            exerciseData.exerciseTitle,
            exerciseData.results,
            exerciseData.exerciseType
        )
    }

    fun removeExercise(id: Long) {
        dbQuery.removeExercise(id)
    }

    private fun getLastInsertedRowId(): Long {
        return dbQuery.selectLastInsertedRowId().executeAsOne()
    }

    private fun mapExercise(
        id: Long,
        categoryId: Long,
        exerciseTitle: String,
        results: List<ResultData>,
        exerciseType: ExerciseType
    ): ExerciseData {
        return ExerciseData(id, categoryId, exerciseTitle, results, exerciseType)
    }
}