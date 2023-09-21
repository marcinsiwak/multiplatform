import pl.msiwak.multiplatfor.dependencies.Deps

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
            baseName = "network"

            export(project(pl.msiwak.multiplatfor.dependencies.Modules.commonObject))
            export(project(pl.msiwak.multiplatfor.dependencies.Modules.utils))
            export(project(pl.msiwak.multiplatfor.dependencies.Modules.auth))

        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.commonObject))
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.utils))
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.auth))

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