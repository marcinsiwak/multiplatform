import pl.msiwak.multiplatfor.dependencies.Deps

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget() {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
jvmToolchain(11)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Remote config Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "remoteConfig"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Deps.Firebase) {
                    api(remoteConfig)
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
    namespace = "pl.msiwak.multiplatform.remoteConfig"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}