import pl.msiwak.multiplatfor.dependencies.Deps
import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.22"
    id("dev.icerock.mobile.multiplatform-resources")
    id("org.jlleitschuh.gradle.ktlint")
}

apply(from = "$rootDir/gradle/buildVariants.gradle")

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

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
            export(project(Modules.commonObject))
            export(project(Modules.commonResources))
            export(project(Modules.utils))
            export(project(Modules.ui))
            export(project(Modules.injector))
            export(project(Modules.notifications))
        }

        xcodeConfigurationToNativeBuildType["productionRelease"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG

        pod("FirebaseCore", linkOnly = true)
        pod("FirebaseAuth", linkOnly = true)
        pod("FirebaseRemoteConfig", linkOnly = true)
        pod("FirebaseCrashlytics", linkOnly = true)
//        pod("GoogleSignIn", linkOnly = true)
        pod("FirebaseMessaging", linkOnly = true)
        pod("Google-Mobile-Ads-SDK", linkOnly = true)

    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.commonObject))
                api(project(Modules.commonResources))
                api(project(Modules.utils))
                api(project(Modules.ui))
                api(project(Modules.injector))
                api(project(Modules.notifications))
            }
        }

        val androidMain by getting {
            dependencies {
                dependsOn(commonMain)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
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
