import pl.msiwak.multiplatfor.dependencies.Modules
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
    iosX64()
    iosArm64()
    iosSimulatorArm64()

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
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.utils))
                api(project(Modules.commonObject))
                api(project(Modules.auth))
                api(project(Modules.database))
                api(project(Modules.network))
                api(project(Modules.remoteConfig))

                with(Deps.Kotlinx) {
                    api(coroutines)
                    api(serialization)
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
    namespace = "pl.msiwak.multiplatform.data"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}