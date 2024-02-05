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
                api(project(Modules.domain))
                api(project(Modules.database))
                api(project(Modules.utils))
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
