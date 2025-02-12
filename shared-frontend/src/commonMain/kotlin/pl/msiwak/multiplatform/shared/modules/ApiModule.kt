package pl.msiwak.multiplatform.shared.modules

import org.koin.dsl.module
import pl.msiwak.multiplatform.network.EngineProvider
import pl.msiwak.multiplatform.network.api.CategoryApi
import pl.msiwak.multiplatform.network.api.UserApi
import pl.msiwak.multiplatform.network.client.KtorClient
import pl.msiwak.multiplatform.remoteConfig.RemoteConfig

val apiModule = module {
    single { KtorClient(get(), get()) }
    single { UserApi(get()) }
    single { CategoryApi(get()) }
    single { EngineProvider() }
    single { RemoteConfig() }
}