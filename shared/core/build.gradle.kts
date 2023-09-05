import pl.msiwak.multiplatfor.dependencies.Deps
import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.22"
    id("app.cash.sqldelight") version "2.0.0-alpha05"
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
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.commonResources))

                with(Deps.Koin) {
                    api(core)
                    api(test)
                }
                with(Deps.Firebase) {
                    api(authentication)
                    api(remoteConfig)
                    api(crashlytics)
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
                with(Deps.SQLDelight) {
                    api(coroutines)
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
                with(Deps.SQLDelight) {
                    api(android)
                }
                with(Deps.Firebase) {
                    api(platform(andoridBom))
                    api(auth)
                }
            }
        }
        val iosMain by getting {
            dependencies {
                with(Deps.Ktor) {
                    api(ios)
                }
                with(Deps.SQLDelight) {
                    api(ios)
                }
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

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("pl.msiwak.multiplatform.shared.core")
        }
    }
}