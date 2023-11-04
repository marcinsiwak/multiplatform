import pl.msiwak.multiplatfor.dependencies.Deps
import pl.msiwak.multiplatfor.dependencies.Modules

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
                jvmTarget = "17"
            }
        }
    }
jvmToolchain(17)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "UI Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "ui"

            export(project(Modules.core))
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.core))
                api(project(Modules.buildConfig))

                with(Deps.Napier) {
                    api(napier)
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
    namespace = "pl.msiwak.multiplatform.ui"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}