package pl.msiwak.multiplatform.database

import pl.msiwak.multiplatform.AppDatabase
import plmsiwakmultiplatformcache.CategoryDB
import plmsiwakmultiplatformcache.ExerciseDB

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