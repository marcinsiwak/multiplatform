package pl.msiwak.multiplatform.database

import pl.msiwak.multiplatform.shared.database.sql.AppDatabase
import plmsiwakmultiplatformdatabasecache.CategoryDB
import plmsiwakmultiplatformdatabasecache.ExerciseDB
import plmsiwakmultiplatformdatabasecache.ResultDB

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
        ),
        ResultDBAdapter = ResultDB.Adapter(
            dateAdapter = localDateTimeAdapter
        )
    )

    fun getDatabaseQueries() = database.appDatabaseQueries
}
