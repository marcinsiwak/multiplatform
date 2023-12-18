import pl.msiwak.multiplatfor.dependencies.Deps
import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("app.cash.sqldelight") version "2.0.0"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
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
        summary = "Database Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "database"

            export(project(Modules.commonObject))
        }
        xcodeConfigurationToNativeBuildType["productionRelease"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] = org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.commonObject))

                with(Deps.SQLDelight) {
                    api(coroutines)
                }
                with(Deps.Kotlinx) {
                    api(coroutines)
                    api(dateTime)
                    api(serialization)
                }
            }
        }
        val androidMain by getting {
            dependencies {
                with(Deps.SQLDelight) {
                    api(android)
                }
            }
        }
        val iosMain by getting {
            dependencies {
                with(Deps.SQLDelight) {
                    api(ios)
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
    namespace = "pl.msiwak.multiplatform.database"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("pl.msiwak.multiplatform.shared.database")
        }
    }
    linkSqlite.set(true)
}
