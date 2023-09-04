package pl.msiwak.multiplatform.android.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import pl.msiwak.multiplatform.core.database.DatabaseDriverFactory


val androidDatabaseModule = module {
    single { DatabaseDriverFactory(androidApplication()) }
}