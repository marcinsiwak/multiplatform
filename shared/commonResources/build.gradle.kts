plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("dev.icerock.mobile.multiplatform-resources")
}


multiplatformResources {
    multiplatformResourcesPackage = "pl.msiwak.multiplatform.commonResources" // required
    iosBaseLocalizationRegion = "en" // optional, default "en"
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
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "commonResources"

            export("dev.icerock.moko:resources:0.21.2")
            export("dev.icerock.moko:graphics:0.9.0") // toUIColor here
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
               api("dev.icerock.moko:resources:0.21.2")
               api("dev.icerock.moko:graphics:0.9.0")

            }
        }
        val androidMain by getting {
            dependencies {
//                with(pl.msiwak.multiplatfor.dependencies.Deps.Firebase) {
//                    api(platform(pl.msiwak.multiplatfor.dependencies.Deps.Firebase.andoridBom))
//                    api(pl.msiwak.multiplatfor.dependencies.Deps.Firebase.auth)
//                }
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
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
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.commonResources"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}