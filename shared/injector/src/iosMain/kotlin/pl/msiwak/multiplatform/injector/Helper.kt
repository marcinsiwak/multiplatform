package pl.msiwak.multiplatform.injector

import cocoapods.GoogleSignIn.GIDSignIn
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin
import org.koin.dsl.module
import pl.msiwak.multiplatform.database.DatabaseDriverFactory
import pl.msiwak.multiplatform.data.remote.repository.VersionRepository
import pl.msiwak.multiplatform.utils.KMMPreferences
import platform.Foundation.NSBundle
import platform.Foundation.NSURL
import platform.darwin.NSObject

fun initKoin() {
    startKoin {
        modules(appModule() + sharedPreferencesModule + iosDatabaseModule + iosRepositoryModule)
    }
}

fun initNapier() {
    Napier.base(DebugAntilog())
}

fun initFirebase() {
    Firebase.initialize()
}

fun initGIDSingIn(url: NSURL): Boolean {
    return GIDSignIn.sharedInstance.handleURL(url)
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
