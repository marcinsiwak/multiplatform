import pl.msiwak.multiplatfor.dependencies.Deps
import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.8.22"
    id("dev.icerock.mobile.multiplatform-resources")
}

apply(from = "$rootDir/gradle/buildVariants.gradle")

kotlin {

    androidTarget {
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
        summary = "Main Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "16.2"

        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = "shared"
            if (System.getenv("XCODE_VERSION_MAJOR") == "1500") {
                linkerOpts += "-ld64"
            }

            compilation.kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")
            compilation.project.setProperty("buildkonfig.flavor", "productionDebug")

            export(Deps.MokoResources.resources)
            export(Deps.MokoResources.graphics)

            export(project(Modules.core))
            export(project(Modules.commonResources))
            export(project(Modules.commonObject))
            export(project(Modules.database))
            export(project(Modules.utils))
            export(project(Modules.auth))
            export(project(Modules.network))
            export(project(Modules.data))
            export(project(Modules.remoteConfig))
            export(project(Modules.domain))
            export(project(Modules.domainImpl))
            export(project(Modules.navigator))
            export(project(Modules.uiWelcome))
            export(project(Modules.uiAddCategory))
            export(project(Modules.uiAddExercise))
            export(project(Modules.uiCategory))
            export(project(Modules.uiDashboard))
            export(project(Modules.uiForceUpdate))
            export(project(Modules.uiLanguage))
            export(project(Modules.uiRegister))
            export(project(Modules.uiSettings))
            export(project(Modules.uiSummary))
            export(project(Modules.uiUnit))
            export(project(Modules.uiVerifyEmail))
            export(project(Modules.buildConfig))
            export(project(Modules.notifications))
        }

        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG

        pod("FirebaseCore", linkOnly = true)
        pod("FirebaseAuth", linkOnly = true)
        pod("FirebaseRemoteConfig", linkOnly = true)
        pod("FirebaseCrashlytics", linkOnly = true)
//        pod("GoogleSignIn", linkOnly = true)
        pod("FirebaseMessaging", linkOnly = true)
        pod("Google-Mobile-Ads-SDK", linkOnly = true)
    }

    sourceSets {

        commonMain.dependencies {
            api(project(Modules.core))
            api(project(Modules.commonResources))
            api(project(Modules.commonObject))
            api(project(Modules.database))
            api(project(Modules.utils))
            api(project(Modules.auth))
            api(project(Modules.network))
            api(project(Modules.data))
            api(project(Modules.remoteConfig))
            api(project(Modules.domain))
            api(project(Modules.domainImpl))
            api(project(Modules.navigator))
            api(project(Modules.uiWelcome))
            api(project(Modules.uiAddCategory))
            api(project(Modules.uiAddExercise))
            api(project(Modules.uiCategory))
            api(project(Modules.uiDashboard))
            api(project(Modules.uiForceUpdate))
            api(project(Modules.uiLanguage))
            api(project(Modules.uiRegister))
            api(project(Modules.uiSettings))
            api(project(Modules.uiSummary))
            api(project(Modules.uiUnit))
            api(project(Modules.uiVerifyEmail))
            api(project(Modules.buildConfig))
            api(project(Modules.notifications))

            with(Deps.Napier) {
                api(napier)
            }

            with(Deps.Koin) {
                implementation(core)
                implementation(test)
            }
        }

        getByName("androidMain").dependsOn(commonMain.get())
        getByName("iosArm64Main").dependsOn(commonMain.get())
        getByName("iosX64Main").dependsOn(commonMain.get())
        getByName("iosSimulatorArm64Main").dependsOn(commonMain.get())

        androidMain.dependencies {
            with(Deps.Koin) {
                implementation(android)
            }
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.android"
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 27
        targetSdk = 33
    }
}
