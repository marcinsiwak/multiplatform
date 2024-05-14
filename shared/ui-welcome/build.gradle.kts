import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("pl.msiwak.convention.android.config")
    id("pl.msiwak.convention.target.config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        summary = "Ui welcome Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "ui-welcome"

            export(project(Modules.navigator))
            export(project(Modules.domain))
            export(project(Modules.utils))
            export(project(Modules.commonResources))
            export(project(Modules.commonObject))
            export(project(Modules.uiCommonComponent))
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
            implementation(project(Modules.domain))
            implementation(project(Modules.navigator))
            implementation(project(Modules.utils))
            implementation(project(Modules.commonResources))
            implementation(project(Modules.commonObject))
            implementation(project(Modules.uiCommonComponent))

            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(libs.kotlinx.lifecycle)
            implementation(libs.kotlinx.viewModel)

            implementation(libs.compose.multiplatform.navigation)

            implementation(libs.napier)
        }

        androidMain.dependencies {
            implementation(libs.google.android.playservices.auth)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.ui.welcome"
}
