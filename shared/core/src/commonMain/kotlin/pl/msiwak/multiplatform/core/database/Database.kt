package pl.msiwak.multiplatform.core.database

import pl.msiwak.multiplatform.shared.core.AppDatabase
import plmsiwakmultiplatformcorecache.CategoryDB
import plmsiwakmultiplatformcorecache.ExerciseDB

class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        CategoryDBAdapter = CategoryDB.Adapter(
            exercisesAdapter = exerciseListAdapter,
            exerciseTypeAdapter = exerciseTypeAdapter
        ),
        ExerciseDBAdapter = ExerciseDB.Adapter(
            resultsAdapter = resultListAdapter,
            exerciseTypeAdapter = exerciseTypeAdapter
        )
    )

    fun getDatabaseQueries() = database.appDatabaseQueries

}