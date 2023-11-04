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
        summary = "Injector Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "injector"

            export(project(Modules.domain))
            export(project(Modules.database))
            export(project(Modules.utils))
            export(project(Modules.ui))
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.domain))
                api(project(Modules.database))
                api(project(Modules.utils))
                api(project(Modules.ui))

                with(Deps.Koin) {
                    api(core)
                    api(test)
                }
            }
        }
        val androidMain by getting {
            dependencies {
                with(Deps.Koin) {
                    api(android)
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
    namespace = "pl.msiwak.multiplatform.injector"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}