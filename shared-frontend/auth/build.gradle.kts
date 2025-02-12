import pl.msiwak.convention.config.baseSetup
import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("pl.msiwak.convention.android.config")
    id("pl.msiwak.convention.target.config")
    id("pl.msiwak.convention.releaseonly.config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        baseSetup()
        framework {
            baseName = "auth"
        }
    }
    sourceSets {
        androidMain.dependencies {
            implementation(libs.firebase.gitlive.auth)
        }

        commonMain.dependencies {
            implementation(project(Modules.commonObject))
            implementation(project(Modules.utils))

            implementation(libs.kotlinx.coroutines)
        }

        iosMain.dependencies {
            implementation(libs.firebase.gitlive.auth)
        }

        wasmJsMain.dependencies {
            implementation(project(Modules.network))
            implementation(npm("firebase", "11.1.0"))
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.auth"
}
