package pl.msiwak.multiplatform.shared

import org.koin.core.module.Module
import org.koin.dsl.module
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepositoryImpl
import pl.msiwak.multiplatform.data.remote.repository.RemoteConfigRepository
import pl.msiwak.multiplatform.data.remote.repository.VersionRepository
import pl.msiwak.multiplatform.data.remote.repository.VersionRepositoryImpl
import pl.msiwak.multiplatform.network.FirebaseClient
import pl.msiwak.multiplatform.utils.KMMPreferences
import pl.msiwak.multiplatform.utils.KMMPreferencesImpl

actual val databaseModule: Module = module {

}

actual val platformRepositoryModule: Module = module {
    single<CategoryRepository> { CategoryRepositoryImpl() }
    single { RemoteConfigRepository(get()) }
    single<KMMPreferences> { KMMPreferencesImpl() }
    single<VersionRepository> { VersionRepositoryImpl() }
    single { FirebaseClient() }

}