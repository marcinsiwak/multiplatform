import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import pl.msiwak.multiplatfor.dependencies.Deps
import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.22"
    id("app.cash.sqldelight") version "2.0.0-alpha05"
    id("dev.icerock.mobile.multiplatform-resources")
    id("com.codingfeline.buildkonfig")
}

dependencies {
    commonMainApi("dev.icerock.moko:resources:0.21.2")
}

multiplatformResources {
    multiplatformResourcesPackage = "pl.msiwak.multiplatform" // required
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
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.2"

        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = "shared"

//            binaryOption("bundleVersion", "1")
            export("dev.icerock.moko:resources:0.21.2")
            export("dev.icerock.moko:graphics:0.9.0") // toUIColor here

//            pod("GoogleSignIn") {
//
//            }
//
        }

        pod("FirebaseCore")
        pod("FirebaseAuth")
    }

//    listOf(
//        iosX64(),
//        iosArm64(),
//    ).forEach {
//        version = "1.0.0"
////        it.binaries.framework {
////            baseName = "shared"
////            isStatic = true
////
////        }
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                with(Deps.Koin) {
                    api(core)
                    api(test)
                }
//                with(Deps.Firebase) {
//                    api(authentication)
//                    api(remoteConfig)
//                    api(crashlytics)
//                }
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
                with(Deps.SQLDelight) {
                    api(ios)
                }
            }
        }
    }
}

buildkonfig {
    packageName = "pl.msiwak.multiplatform"

    val releasePropertiesFile = rootProject.file("release.properties")
    val releaseProperties = Properties()
    releaseProperties.load(FileInputStream(releasePropertiesFile))

    defaultConfigs {
        buildConfigField(STRING, "BASE_URL", releaseProperties["BASE_URL"] as String)
    }
    targetConfigs {
        create("ios") {
            buildConfigField(STRING, "BASE_URL", releaseProperties["BASE_URL"] as String)
        }
    }
    targetConfigs("debug") {
        
        val debugPropertiesFile = rootProject.file("debug.properties")
        val debugProperties = Properties()
        debugProperties.load(FileInputStream(debugPropertiesFile))
        
        create("android") {
            buildConfigField(STRING, "BASE_URL", debugProperties["BASE_URL"] as String)
        }
        create("ios") {
            buildConfigField(STRING, "BASE_URL", debugProperties["BASE_URL"] as String)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.android"
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 27
        targetSdk = 33
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("pl.msiwak.multiplatform")
        }
    }
}