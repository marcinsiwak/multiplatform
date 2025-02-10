import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinCompose)
    id("pl.msiwak.convention.android.config")
    id("pl.msiwak.convention.target.config")
    id("pl.msiwak.convention.releaseonly.config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        summary = "Ui welcome Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "ui-common-component"
        }
        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG

        pod("Google-Mobile-Ads-SDK", moduleName = "GoogleMobileAds")
        pod("FirebaseCore")
        pod("GoogleSignIn")
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.commonResources))
            implementation(project(Modules.commonObject))
            implementation(project(Modules.buildConfig))

            implementation(libs.kotlinx.lifecycle)
            implementation(libs.kotlinx.viewModel)
            implementation(libs.compose.multiplatform.navigation)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.kermit)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.google.android.playservices.auth)
            implementation(libs.google.android.playservices.ads)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.ui.commonComponent"
}
