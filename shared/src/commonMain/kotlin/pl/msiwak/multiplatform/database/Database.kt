package pl.msiwak.multiplatform.database

import pl.msiwak.multiplatform.cache.AppDatabase

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        CategoryAdapter = plmsiwakmultiplatformcache.Category.Adapter(
            exercisesAdapter = exerciseListAdapter,
            exerciseTypeAdapter = exerciseTypeAdapter
        ),
        ExerciseAdapter = plmsiwakmultiplatformcache.Exercise.Adapter(
            resultsAdapter = resultListAdapter,
            exerciseTypeAdapter = exerciseTypeAdapter
        )
    )

    fun getDatabaseQueries() = database.appDatabaseQueries

}