package pl.msiwak.multiplatform.shared

import cocoapods.GoogleMobileAds.GADMobileAds
import cocoapods.GoogleSignIn.GIDSignIn
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.context.startKoin
import pl.msiwak.multiplatform.shared.modules.sharedPreferencesModule
import platform.Foundation.NSURL

fun initKoin() {
    startKoin {
        modules(appModule() + sharedPreferencesModule)
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

