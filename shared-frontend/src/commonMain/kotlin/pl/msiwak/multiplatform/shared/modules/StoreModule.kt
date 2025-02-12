package pl.msiwak.multiplatform.shared.modules

import org.koin.dsl.module
import pl.msiwak.multiplatform.data.local.store.LanguageStore
import pl.msiwak.multiplatform.data.local.store.OfflineStore
import pl.msiwak.multiplatform.data.local.store.UnitStore
import pl.msiwak.multiplatform.store.SessionStore

val storeModule = module {
    single { LanguageStore(get()) }
    single { UnitStore(get()) }
    single { OfflineStore(get()) }
    single { SessionStore(get()) }
}