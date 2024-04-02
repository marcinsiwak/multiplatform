plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
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
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "notifications"
        }

        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG

//        pod("FirebaseMessaging")
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.napier)
        }

        androidMain.dependencies {
            implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
            implementation(libs.firebase.andorid.messaging)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.notifications"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
