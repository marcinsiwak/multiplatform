package pl.msiwak.multiplatform.shared.modules

import org.koin.core.module.Module
import org.koin.dsl.module
import pl.msiwak.multiplatform.auth.FirebaseAuthorization
import pl.msiwak.multiplatform.auth.FirebaseAuthorizationImpl
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepositoryImpl
import pl.msiwak.multiplatform.data.remote.repository.RemoteConfigRepository
import pl.msiwak.multiplatform.data.remote.repository.VersionRepository
import pl.msiwak.multiplatform.data.remote.repository.VersionRepositoryImpl
import pl.msiwak.multiplatform.utils.KMPPreferences
import pl.msiwak.multiplatform.utils.KMPPreferencesImpl

actual val databaseModule: Module = module {}

actual val platformRepositoryModule: Module = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    single { RemoteConfigRepository(get()) }
    single<KMPPreferences> { KMPPreferencesImpl() }
    single<VersionRepository> { VersionRepositoryImpl() }
}

actual val authModule = module {
    single<FirebaseAuthorization> { FirebaseAuthorizationImpl() }
}