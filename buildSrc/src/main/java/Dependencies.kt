package pl.msiwak.multiplatfor.dependencies

import pl.msiwak.multiplatfor.dependencies.Versions.firebaseGitliveVersion
import pl.msiwak.multiplatfor.dependencies.Versions.koinComposeVersion
import pl.msiwak.multiplatfor.dependencies.Versions.koinVersion
import pl.msiwak.multiplatfor.dependencies.Versions.ktorVersion
import pl.msiwak.multiplatfor.dependencies.Versions.mokoGraphicsVersion
import pl.msiwak.multiplatfor.dependencies.Versions.mokoResourcesVersion
import pl.msiwak.multiplatfor.dependencies.Versions.sqlDelightVersion

object Versions {
    const val koinVersion = "3.3.2"
    const val koinComposeVersion = "3.4.2"
    const val ktorVersion = "2.3.1"
    const val sqlDelightVersion = "2.0.0"
    const val mokoResourcesVersion = "0.23.0"
    const val mokoGraphicsVersion = "0.9.0"
    const val firebaseGitliveVersion = "1.10.0"
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
        const val authentication = "dev.gitlive:firebase-auth:$firebaseGitliveVersion"
        const val remoteConfig = "dev.gitlive:firebase-config:$firebaseGitliveVersion"
        const val crashlytics = "dev.gitlive:firebase-crashlytics:$firebaseGitliveVersion"

        const val andoridBom = "com.google.firebase:firebase-bom:32.3.1"
        const val messaging = "com.google.firebase:firebase-messaging-ktx"
    }

    object Kotlinx {
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0" //todo add zones to datetime https://github.com/Kotlin/kotlinx-datetime
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0"
    }


    object Ktor {
        const val core = "io.ktor:ktor-client-core:$ktorVersion"
        const val android = "io.ktor:ktor-client-android:$ktorVersion"
        const val ios = "io.ktor:ktor-client-darwin:$ktorVersion"
        const val content_negation = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
        const val cio = "io.ktor:ktor-client-cio:$ktorVersion"
        const val logger = "io.ktor:ktor-client-logging:$ktorVersion"
    }

    object SQLDelight {

        const val runtime = "com.squareup.sqldelight:runtime:$sqlDelightVersion"
        const val android = "app.cash.sqldelight:android-driver:$sqlDelightVersion"
        const val ios = "app.cash.sqldelight:native-driver:$sqlDelightVersion"
        const val coroutines = "app.cash.sqldelight:coroutines-extensions:$sqlDelightVersion"
    }

    object Napier {
        const val napier = "io.github.aakira:napier:2.6.1"
    }

    object Google {
        const val andorid_play_services_auth= "com.google.android.gms:play-services-auth:20.6.0"
    }

    object MokoResources {
        const val resources ="dev.icerock.moko:resources:$mokoResourcesVersion"
        const val graphics = "dev.icerock.moko:graphics:$mokoGraphicsVersion"
    }
}