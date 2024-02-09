import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
}

apply(from = "$rootDir/gradle/buildVariants.gradle")

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
        summary = "Domain Implementation Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "domain-impl"

            export(project(Modules.data))
            export(project(Modules.domain))
            export(project(Modules.commonObject))
            export(project(Modules.commonResources))
            export(project(Modules.utils))
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
            implementation(project(Modules.data))
            implementation(project(Modules.commonObject))
            implementation(project(Modules.commonResources))
            implementation(project(Modules.utils))

            implementation(libs.napier)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.domainimpl"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
