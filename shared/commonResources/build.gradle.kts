plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("dev.icerock.mobile.multiplatform-resources")
    alias(libs.plugins.composeMultiplatform)
    id("pl.msiwak.convention.target.config")
    id("pl.msiwak.convention.android.config")
}

multiplatformResources {
    multiplatformResourcesPackage = "pl.msiwak.multiplatform.commonResources" // required
    iosBaseLocalizationRegion = "en" // optional, default "en"
    multiplatformResourcesClassName = "SR"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        summary = "CommonResources for the Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "commonResources"

            export(libs.moko.resources)
            export(libs.moko.resources.compose)
            export(libs.moko.graphics)
        }
        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
    }

    sourceSets {
        getByName("androidMain").dependsOn(commonMain.get())
        getByName("iosArm64Main").dependsOn(commonMain.get())
        getByName("iosX64Main").dependsOn(commonMain.get())
        getByName("iosSimulatorArm64Main").dependsOn(commonMain.get())

        commonMain.dependencies {
            api(libs.moko.resources)
            api(libs.moko.resources.compose)
            api(libs.moko.graphics)

            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.ui)
            api(compose.components.resources)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.commonResources"
}
