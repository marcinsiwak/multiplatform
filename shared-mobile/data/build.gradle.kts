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
        summary = "Data Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "data"

            export(project(Modules.utils))
            export(project(Modules.commonObject))
            export(project(Modules.auth))
            export(project(Modules.database))
            export(project(Modules.network))
            export(project(Modules.remoteConfig))
            export(project(Modules.store))
        }
        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
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
