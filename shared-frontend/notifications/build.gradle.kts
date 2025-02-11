import pl.msiwak.convention.config.baseSetup
import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("pl.msiwak.convention.target.config")
    id("pl.msiwak.convention.releaseonly.config")
    id("pl.msiwak.convention.android.config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        baseSetup()
        framework {
            baseName = "notifications"
        }

        pod("FirebaseMessaging")
    }

    sourceSets {
        androidMain.dependencies {
            implementation(project.dependencies.platform("com.google.firebase:firebase-bom:33.1.0"))
            implementation(libs.firebase.andorid.messaging)
            implementation(libs.koin.core)

        }

        commonMain.dependencies {
            implementation(project(Modules.store))
            implementation(libs.kermit)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.notifications"
}
