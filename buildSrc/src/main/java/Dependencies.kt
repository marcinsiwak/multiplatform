package pl.msiwak.multiplatfor.dependencies

import pl.msiwak.multiplatfor.dependencies.Versions.koinComposeVersion
import pl.msiwak.multiplatfor.dependencies.Versions.koinVersion
import pl.msiwak.multiplatfor.dependencies.Versions.ktorVersion
import pl.msiwak.multiplatfor.dependencies.Versions.sqlDelightVersion

object Versions {
    const val koinVersion = "3.3.2"
    const val koinComposeVersion = "3.4.2"
    const val ktorVersion = "2.2.1"
    const val sqlDelightVersion = "2.0.0-alpha05"
}

object Deps {

    object Koin {
        const val core = "io.insert-koin:koin-core:$koinVersion"
        const val test = "io.insert-koin:koin-test:$koinVersion"
        const val android = "io.insert-koin:koin-android:$koinVersion"
        const val navigation = "io.insert-koin:koin-androidx-navigation:$koinVersion"
        const val compose = "io.insert-koin:koin-androidx-compose:$koinComposeVersion"
    }

    object Firebase {
        const val authentication = "dev.gitlive:firebase-auth:1.6.2"
        const val remoteConfig = "dev.gitlive:firebase-config:1.8.0"
        const val crashlytics = "dev.gitlive:firebase-crashlytics:1.8.0"
    }

    object Kotlinx {
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0" //todo add zones to datetime https://github.com/Kotlin/kotlinx-datetime
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"
    }


    object Ktor {
        const val core = "io.ktor:ktor-client-core:$ktorVersion"
        const val android = "io.ktor:ktor-client-android:$ktorVersion"
        const val ios = "io.ktor:ktor-client-darwin:$ktorVersion"
    }

    object SQLDelight {

        const val runtime = "com.squareup.sqldelight:runtime:$sqlDelightVersion"
        const val android = "app.cash.sqldelight:android-driver:$sqlDelightVersion"
        const val ios = "app.cash.sqldelight:native-driver:$sqlDelightVersion"
        const val coroutines = "app.cash.sqldelight:coroutines-extensions:$sqlDelightVersion"
    }

    // todo change napier to timber when ready
    object Napier {
        const val napier = "io.github.aakira:napier:2.6.1"
    }

}