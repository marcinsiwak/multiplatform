package pl.msiwak.multiplatform.helpers

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin
import org.koin.dsl.module
import pl.msiwak.multiplatform.database.DatabaseDriverFactory
import pl.msiwak.multiplatform.di.appModule
import pl.msiwak.multiplatform.repository.VersionRepository
import pl.msiwak.multiplatform.utils.KMMPreferences
import platform.Foundation.NSBundle
import platform.darwin.NSObject

fun initKoin() {
    startKoin {
        modules(appModule() + sharedPreferencesModule + iosDatabaseModule + iosRepositoryModule)
    }
}

fun initNapier() {
    Napier.base(DebugAntilog())
}

val sharedPreferencesModule = module {
    single { KMMPreferences(NSObject()) }
}

val iosRepositoryModule = module {
    single { VersionRepository(NSBundle.mainBundle()) }
}

val iosDatabaseModule = module {
    single { DatabaseDriverFactory() }
}
