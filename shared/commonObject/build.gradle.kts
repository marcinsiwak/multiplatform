import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.8.22"
}

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
        summary = "CommonObject Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "commonObject"

            export(project(Modules.commonResources))
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
            implementation(project(Modules.commonResources))

            api(libs.kotlinx.dateTime)
            implementation(libs.kotlinx.serialization)

            api(libs.firebase.gitlive.auth)
            api(libs.firebase.gitlive.remoteConfig)
        }


        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.commonObject"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
