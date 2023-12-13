import pl.msiwak.multiplatfor.dependencies.Deps

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform-resources")
}


multiplatformResources {
    multiplatformResourcesPackage = "pl.msiwak.multiplatform.commonResources" // required
    iosBaseLocalizationRegion = "en" // optional, default "en"
}

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
        summary = "CommonResources for the Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "commonResources"

            export(Deps.MokoResources.resources)
            export(Deps.MokoResources.graphics)
        }
        xcodeConfigurationToNativeBuildType["productionRelease"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Deps.MokoResources) {
                    api(resources)
                    api(graphics)
                }
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)

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
    namespace = "pl.msiwak.multiplatform.commonResources"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}