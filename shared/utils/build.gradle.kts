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
            baseName = "utils"

        }

        pod("FirebaseCore")
        pod("FirebaseAuth")
        pod("FirebaseRemoteConfig")
        pod("FirebaseCrashlytics")
        pod("GoogleSignIn")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                with(pl.msiwak.multiplatfor.dependencies.Deps.Firebase) {
                    api(pl.msiwak.multiplatfor.dependencies.Deps.Firebase.authentication)
                    api(pl.msiwak.multiplatfor.dependencies.Deps.Firebase.remoteConfig)
                    api(pl.msiwak.multiplatfor.dependencies.Deps.Firebase.crashlytics)
                }
            }
        }

        val androidMain by getting {
            dependencies {
                with(pl.msiwak.multiplatfor.dependencies.Deps.Firebase) {
                    api(platform(pl.msiwak.multiplatfor.dependencies.Deps.Firebase.andoridBom))
                    api(pl.msiwak.multiplatfor.dependencies.Deps.Firebase.auth)
                }
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.utils"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}