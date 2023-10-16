package pl.msiwak.multiplatform.database

import pl.msiwak.multiplatform.shared.database.AppDatabase
import plmsiwakmultiplatformdatabasecache.CategoryDB
import plmsiwakmultiplatformdatabasecache.ExerciseDB

class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        CategoryDBAdapter = CategoryDB.Adapter(
            exerciseTypeAdapter = exerciseTypeAdapter,
            creationDateAdapter = localDateTimeAdapter
        ),
        ExerciseDBAdapter = ExerciseDB.Adapter(
            exerciseTypeAdapter = exerciseTypeAdapter,
            creationDateAdapter = localDateTimeAdapter
        )
    )

    fun getDatabaseQueries() = database.appDatabaseQueries

}