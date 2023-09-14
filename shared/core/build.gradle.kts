import pl.msiwak.multiplatfor.dependencies.Deps
import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.22"
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
            baseName = "core"

            export(project(Modules.commonResources))
            export(project(Modules.commonObject))
            export(project(Modules.database))
            export(project(Modules.utils))
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.commonResources))
                api(project(Modules.commonObject))
                api(project(Modules.database))
                api(project(Modules.utils))

                with(Deps.Koin) {
                    api(core)
                    api(test)
                }
                with(Deps.Firebase) {
                    implementation(authentication)
                    implementation(remoteConfig)
                    implementation(crashlytics)
                }
                with(Deps.Kotlinx) {
                    api(coroutines)
                    api(dateTime)
                    api(serialization)
                }
                with(Deps.Ktor) {
                    api(core)
                    api(content_negation)
                    api(serialization)
                    api(cio)
                    api(logger)
                }
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

        val androidMain by getting {
            dependencies {
                with(Deps.Koin) {
                    api(android)
                }
                with(Deps.Ktor) {
                    api(android)
                }
//                with(Deps.SQLDelight) {
//                    api(android)
//                }
                with(Deps.Firebase) {
                    api(platform(andoridBom))
                    api(auth)
                }
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
            dependencies {
                with(Deps.Ktor) {
                    api(ios)
                }
//                with(Deps.SQLDelight) {
//                    api(ios)
//                }
            }
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.core"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
