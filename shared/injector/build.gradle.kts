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
            baseName = "injector"

            export(project(pl.msiwak.multiplatfor.dependencies.Modules.domain))
            export(project(pl.msiwak.multiplatfor.dependencies.Modules.database))
            export(project(pl.msiwak.multiplatfor.dependencies.Modules.utils))
            export(project(pl.msiwak.multiplatfor.dependencies.Modules.ui))
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.domain))
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.database))
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.utils))
                api(project(pl.msiwak.multiplatfor.dependencies.Modules.ui))

                with(pl.msiwak.multiplatfor.dependencies.Deps.Koin) {
                    api(pl.msiwak.multiplatfor.dependencies.Deps.Koin.core)
                    api(pl.msiwak.multiplatfor.dependencies.Deps.Koin.test)
                }
            }
        }
        val androidMain by getting {
            dependencies {
                with(pl.msiwak.multiplatfor.dependencies.Deps.Koin) {
                    api(pl.msiwak.multiplatfor.dependencies.Deps.Koin.android)
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
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}