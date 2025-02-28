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
import pl.msiwak.multiplatform.database.dao.CategoriesDao
import pl.msiwak.multiplatform.database.dao.ExercisesDao
import pl.msiwak.multiplatform.database.dao.ResultsDao
import pl.msiwak.multiplatform.utils.KMPPreferences
import pl.msiwak.multiplatform.utils.KMPPreferencesImpl

actual val databaseModule: Module = module {
    single { Database(get()) }
    single { CategoriesDao(get()) }
    single { ExercisesDao(get()) }
    single { ResultsDao(get()) }
}

actual val platformRepositoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get(), get(), get(), get()) }
    single<KMPPreferences> { KMPPreferencesImpl(get()) }
    single<VersionRepository> { VersionRepositoryImpl(get()) }
}

actual val authModule = module {
    single<FirebaseAuthorization> { FirebaseAuthorizationImpl() }
}