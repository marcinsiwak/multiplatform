import pl.msiwak.convention.config.baseSetup
import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    id("pl.msiwak.convention.android.config")
    id("pl.msiwak.convention.target.config")
}

apply(from = "$rootDir/gradle/buildVariants.gradle")

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        baseSetup()
        framework {
            baseName = "network"
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.cio)
        }
        commonMain.dependencies {
            implementation(project(Modules.commonObject))
            implementation(project(Modules.buildConfig))
            implementation(project(Modules.store))

            implementation(libs.ktor.core)
            implementation(libs.ktor.contentNegation)
            implementation(libs.ktor.serialization)
            implementation(libs.ktor.logger)

            implementation(libs.kermit)

            implementation(libs.kotlinx.serialization)

            implementation(project(Modules.sharedModel))
            implementation(project(Modules.sharedUtils))
        }

        androidMain.dependencies {
            implementation(libs.ktor.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.ios)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.network"
}
