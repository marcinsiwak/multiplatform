package pl.msiwak.multiplatform.database

import pl.msiwak.multiplatform.shared.database.AppDatabase
import plmsiwakmultiplatformdatabasecache.CategoryDB

class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = AppDatabase(
        databaseDriverFactory.createDriver(),
        CategoryDBAdapter = CategoryDB.Adapter(
            exercisesAdapter = exerciseListAdapter,
            exerciseTypeAdapter = exerciseTypeAdapter,
            creationDateAdapter = localDateTimeAdapter
        )
    )

    fun getDatabaseQueries() = database.appDatabaseQueries

}