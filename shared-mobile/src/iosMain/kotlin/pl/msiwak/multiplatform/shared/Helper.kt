package pl.msiwak.multiplatform.shared

import cocoapods.GoogleMobileAds.GADMobileAds
import cocoapods.GoogleSignIn.GIDSignIn
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.context.startKoin
import org.koin.dsl.module
import pl.msiwak.multiplatform.data.remote.repository.VersionRepository
import pl.msiwak.multiplatform.data.remote.repository.VersionRepositoryImpl
import pl.msiwak.multiplatform.database.DatabaseDriverFactory
import pl.msiwak.multiplatform.utils.KMMPreferences
import pl.msiwak.multiplatform.utils.KMMPreferencesImpl
import platform.Foundation.NSBundle
import platform.Foundation.NSURL

fun initKoin() {
    startKoin {
        modules(appModule() + sharedPreferencesModule + iosDatabaseModule + iosRepositoryModule)
    }
}

@OptIn(ExperimentalForeignApi::class)
fun initMobileAds() {
    GADMobileAds.sharedInstance().startWithCompletionHandler(null)
//    GADMobileAds.sharedInstance().requestConfiguration.testDeviceIdentifiers = listOf("b7496249c8b3b0419541121ffbd27c57")
}

fun initFirebase() {
    Firebase.initialize()
}

@OptIn(ExperimentalForeignApi::class)
fun initGIDSingIn(url: NSURL): Boolean {
    return GIDSignIn.sharedInstance().handleURL(url)
}

val sharedPreferencesModule = module {
    single<KMMPreferences> { KMMPreferencesImpl(get()) }
}

val iosRepositoryModule = module {
    single { VersionRepositoryImpl(NSBundle.mainBundle()) }
    single<VersionRepository> { VersionRepositoryImpl(get()) }

}

val iosDatabaseModule = module {
    single { DatabaseDriverFactory() }
}
