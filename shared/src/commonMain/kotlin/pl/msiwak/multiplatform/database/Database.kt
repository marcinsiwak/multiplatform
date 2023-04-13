package pl.msiwak.multiplatform.database

import pl.msiwak.multiplatform.AppDatabase
import plmsiwakmultiplatformcache.Category
import plmsiwakmultiplatformcache.Exercise

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        CategoryAdapter = Category.Adapter(
            exercisesAdapter = exerciseListAdapter,
            exerciseTypeAdapter = exerciseTypeAdapter
        ),
        ExerciseAdapter = Exercise.Adapter(
            resultsAdapter = resultListAdapter,
            exerciseTypeAdapter = exerciseTypeAdapter
        )
    )

    fun getDatabaseQueries() = database.appDatabaseQueries

}