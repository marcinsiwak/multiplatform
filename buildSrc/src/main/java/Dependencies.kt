package pl.msiwak.multiplatfor.dependencies

object Versions {
    const val koin = "3.3.2"
}

object Deps {

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val navigation = "io.insert-koin:koin-androidx-navigation:${Versions.koin}"
    }

    object Firebase {
        const val authentication = "dev.gitlive:firebase-auth:1.6.2"
    }

    object Kotlinx {
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    }

    object Ktor {
        const val ktor = "io.ktor:ktor-client-core:2.2.2"
        const val ktorAndroid = "io.ktor:ktor-client-okhttp:2.2.2"
        const val ktorIOS = "io.ktor:ktor-client-darwin:2.2.2"
    }

    // todo change napier to timber when ready
    object Napier {
        const val napier = "io.github.aakira:napier:2.6.1"
    }

}