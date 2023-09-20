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
            baseName = "data"

            export(project(pl.msiwak.multiplatfor.dependencies.Modules.utils))
            export(project(pl.msiwak.multiplatfor.dependencies.Modules.commonObject))
            export(project(pl.msiwak.multiplatfor.dependencies.Modules.auth))
            export(project(pl.msiwak.multiplatfor.dependencies.Modules.database))
            export(project(pl.msiwak.multiplatfor.dependencies.Modules.network))
            export(project(pl.msiwak.multiplatfor.dependencies.Modules.remoteConfig))
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.utils))
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.commonObject))
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.auth))
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.database))
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.network))
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.remoteConfig))

                with(pl.msiwak.multiplatfor.dependencies.Deps.Kotlinx) {
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
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}