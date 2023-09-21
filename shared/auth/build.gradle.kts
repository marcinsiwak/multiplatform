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
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
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
                with(Deps.Firebase) {
                    api(platform(andoridBom))
                    api(auth)
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
    namespace = "pl.msiwak.multiplatform.auth"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}