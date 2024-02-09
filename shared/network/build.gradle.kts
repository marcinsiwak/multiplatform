import pl.msiwak.multiplatfor.dependencies.Deps
import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.8.22"
    id("com.android.library")
}

apply(from = "$rootDir/gradle/buildVariants.gradle")

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {


    androidTarget() {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    jvmToolchain(17)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Network Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "network"

            export(project(Modules.commonObject))
            export(project(Modules.buildConfig))
            export(project(Modules.auth))
        }
        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.commonObject))
            implementation(project(Modules.buildConfig))
            implementation(project(Modules.auth))

            with(Deps.Ktor) {
                implementation(core)
                implementation(content_negation)
                implementation(serialization)
                implementation(cio)
                implementation(logger)
            }

            with(Deps.Napier) {
                implementation(napier)
            }
        }

        androidMain.dependencies {
            with(Deps.Ktor) {
                implementation(android)
            }
        }

        iosMain.dependencies {
            with(Deps.Ktor) {
                api(ios)
            }
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.network"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
