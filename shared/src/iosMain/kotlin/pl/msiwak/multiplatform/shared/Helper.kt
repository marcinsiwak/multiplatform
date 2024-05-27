package pl.msiwak.multiplatform.shared

//import cocoapods.GoogleMobileAds.GADMobileAds
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.context.startKoin
import org.koin.dsl.module
import pl.msiwak.multiplatform.data.remote.repository.VersionRepository
import pl.msiwak.multiplatform.database.DatabaseDriverFactory
import pl.msiwak.multiplatform.utils.KMMPreferences
import platform.Foundation.NSBundle
import platform.Foundation.NSURL
import platform.darwin.NSObject

fun initKoin() {
    startKoin {
        modules(appModule() + sharedPreferencesModule + iosDatabaseModule + iosRepositoryModule)
    }
}

@OptIn(ExperimentalForeignApi::class)
fun initMobileAds() {
//    GADMobileAds.sharedInstance().startWithCompletionHandler(null)
//    GADMobileAds.sharedInstance().requestConfiguration.testDeviceIdentifiers = listOf("b7496249c8b3b0419541121ffbd27c57")
}

fun initNapier() {
    Napier.base(DebugAntilog())
}

fun initFirebase() {
    Firebase.initialize()
}

fun initGIDSingIn(url: NSURL): Boolean {
//    return GIDSignIn.sharedInstance.handleURL(url)
    return false
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
