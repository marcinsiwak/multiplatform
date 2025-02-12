package pl.msiwak.multiplatform.shared.modules

import org.koin.dsl.module
import pl.msiwak.multiplatform.data.remote.repository.AuthRepository
import pl.msiwak.multiplatform.data.remote.repository.RemoteConfigRepository
import pl.msiwak.multiplatform.data.remote.repository.SessionRepository
import pl.msiwak.multiplatform.data.remote.repository.UserRepository

val repositoryModule = module {
    single { AuthRepository(get()) }
    single { UserRepository(get()) }
    single { RemoteConfigRepository(get()) }
    single { SessionRepository(get(), get()) }
}