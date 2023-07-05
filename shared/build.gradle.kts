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
//    id("dev.icerock.mobile.multiplatform-resources")
    id("com.codingfeline.buildkonfig")
}

dependencies {
//    commonMainApi("dev.icerock.moko:resources:0.21.2")
}

//multiplatformResources {
//    multiplatformResourcesPackage = "pl.msiwak.multiplatform" // required
//    iosBaseLocalizationRegion = "en" // optional, default "en"
//}

kotlin {
    android()

    cocoapods {
        version = "1.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        framework {
            isStatic = false

//            binaryOption("bundleVersion", "1")
//            export("dev.icerock.moko:resources:0.21.2")
//            export("dev.icerock.moko:graphics:0.9.0") // toUIColor here

//            pod("GoogleSignIn") {
//
//            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        version = "1.0.0"
//        it.binaries.framework {
//            baseName = "shared"
//            isStatic = true
//
//        }
    }

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
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
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
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
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