package pl.msiwak.multiplatform.shared

import org.koin.core.module.Module
import org.koin.dsl.module
import pl.msiwak.multiplatform.database.Database
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.ExercisesDao
import pl.msiwak.multiplatform.database.dao.ResultsDao

actual val databaseModule: Module = module {
    single { Database(get()) }
    single { CategoriesDao(get()) }
    single { ExercisesDao(get()) }
    single { ResultsDao(get()) }
}