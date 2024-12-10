package pl.msiwak.multiplatform.android.di

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import pl.msiwak.multiplatform.data.remote.repository.VersionRepository
import pl.msiwak.multiplatform.data.remote.repository.VersionRepositoryImpl

val androidRepositoryModule = module {
    single<VersionRepository> { VersionRepositoryImpl(androidApplication()) }
}
