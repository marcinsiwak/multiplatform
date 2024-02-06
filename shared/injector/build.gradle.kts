import pl.msiwak.multiplatfor.dependencies.Deps
import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
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
        summary = "Injector Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "injector"

            export(project(Modules.domain))
            export(project(Modules.domainImpl))
            export(project(Modules.database))
            export(project(Modules.utils))
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
            export(project(Modules.core))
            export(project(Modules.data))
            export(project(Modules.auth))
            export(project(Modules.network))
            export(project(Modules.remoteConfig))
        }
        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Modules.domain))
                implementation(project(Modules.domainImpl))
                implementation(project(Modules.database))
                implementation(project(Modules.utils))
                implementation(project(Modules.navigator))
                implementation(project(Modules.uiWelcome))
                implementation(project(Modules.uiAddCategory))
                implementation(project(Modules.uiAddExercise))
                implementation(project(Modules.uiCategory))
                implementation(project(Modules.uiDashboard))
                implementation(project(Modules.uiForceUpdate))
                implementation(project(Modules.uiLanguage))
                implementation(project(Modules.uiRegister))
                implementation(project(Modules.uiSettings))
                implementation(project(Modules.uiSummary))
                implementation(project(Modules.uiUnit))
                implementation(project(Modules.uiVerifyEmail))
                implementation(project(Modules.core))
                implementation(project(Modules.data))
                implementation(project(Modules.auth))
                implementation(project(Modules.network))
                implementation(project(Modules.remoteConfig))

                with(Deps.Koin) {
                    api(core)
                    api(test)
                }
            }
        }
        val androidMain by getting {
            dependencies {
                with(Deps.Koin) {
                    api(android)
                }
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.injector"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
