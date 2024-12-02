package pl.msiwak.multiplatform.shared

import org.koin.core.module.Module
import org.koin.dsl.module
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepository
import pl.msiwak.multiplatform.data.remote.repository.CategoryRepositoryImpl
import pl.msiwak.multiplatform.data.remote.repository.RemoteConfigRepository

actual val databaseModule: Module = module {

}

actual val platformRepositoryModule: Module = module {
    single<CategoryRepository> { CategoryRepositoryImpl() }
    single { RemoteConfigRepository(get()) }

}