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
        summary = "Network Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "network"

            export(project(Modules.commonObject))
            export(project(Modules.utils))
            export(project(Modules.auth))

        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.commonObject))
                api(project(Modules.utils))
                api(project(Modules.auth))

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

        val androidMain by getting {
            dependencies {
                with(Deps.Ktor) {
                    api(android)
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
    namespace = "pl.msiwak.multiplatform.network"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}