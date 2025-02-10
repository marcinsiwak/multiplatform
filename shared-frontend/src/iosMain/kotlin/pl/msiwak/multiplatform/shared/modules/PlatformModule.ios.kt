package pl.msiwak.multiplatform.shared.modules

import org.koin.core.module.Module
import org.koin.dsl.module
import pl.msiwak.multiplatform.auth.FirebaseAuthorization
import pl.msiwak.multiplatform.auth.FirebaseAuthorizationImpl
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepositoryImpl
import pl.msiwak.multiplatform.data.remote.repository.VersionRepository
import pl.msiwak.multiplatform.data.remote.repository.VersionRepositoryImpl
import pl.msiwak.multiplatform.database.Database
import pl.msiwak.multiplatform.database.DatabaseDriverFactory
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.ExercisesDao
import pl.msiwak.multiplatform.database.dao.ResultsDao
import pl.msiwak.multiplatform.utils.KMPPreferences
import pl.msiwak.multiplatform.utils.KMPPreferencesImpl
import platform.Foundation.NSBundle
import platform.darwin.NSObject


actual val databaseModule: Module = module {
    single { Database(get()) }
    single { CategoriesDao(get()) }
    single { ExercisesDao(get()) }
    single { ResultsDao(get()) }
    single { DatabaseDriverFactory() }
}

actual val platformRepositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get(), get(), get(), get()) }
    single<VersionRepository> { VersionRepositoryImpl(NSBundle.mainBundle()) }
}

actual val authModule = module {
    single<FirebaseAuthorization> { FirebaseAuthorizationImpl() }
}

val sharedPreferencesModule = module {
    single<KMPPreferences> { KMPPreferencesImpl(NSObject()) }
}