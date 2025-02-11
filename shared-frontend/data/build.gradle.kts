import pl.msiwak.convention.config.baseSetup
import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("pl.msiwak.convention.target.config")
    id("pl.msiwak.convention.android.config")
}

apply(from = "$rootDir/gradle/buildVariants.gradle")

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        baseSetup()
        framework {
            baseName = "data"
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(project(Modules.database))
        }
        commonMain.dependencies {
            implementation(project(Modules.utils))
            implementation(project(Modules.commonObject))
            implementation(project(Modules.auth))
            implementation(project(Modules.network))
            implementation(project(Modules.remoteConfig))
            implementation(project(Modules.store))

            implementation(libs.kotlinx.coroutines)

            implementation(project(Modules.sharedModel))
        }

        iosMain.dependencies {
            implementation(project(Modules.database))
        }

        wasmJsMain.dependencies {
            implementation(project(Modules.databaseWasm))
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.data"
}
