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

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Auth Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "auth"

            export(project(Modules.utils))

        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.utils))

                with(Deps.Firebase) {
                    api(authentication)
                }
                with(Deps.Kotlinx) {
                    api(coroutines)
                }
            }
        }
        val androidMain by getting {
            dependencies {
                dependsOn(commonMain)
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
    namespace = "pl.msiwak.multiplatform.auth"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}