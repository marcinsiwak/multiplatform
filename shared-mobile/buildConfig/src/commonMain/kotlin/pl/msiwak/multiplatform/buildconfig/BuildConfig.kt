package pl.msiwak.multiplatform.buildconfig

import pl.msiwak.multiplatform.shared.buildConfig.BuildKonfig

object BuildConfig {
    val BASE_URL: String = BuildKonfig.BASE_URL
    val IsDebug: Boolean = BuildKonfig.IsDebug
    val firebaseKey: String = BuildKonfig.FIREBASE_KEY
    val buildFlavour: String = BuildKonfig.BUILD_FLAVOUR
}