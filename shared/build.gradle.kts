import pl.msiwak.multiplatfor.dependencies.Deps

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.22"
    id("app.cash.sqldelight") version "2.0.0-alpha05"
    id("dev.icerock.mobile.multiplatform-resources")
}

dependencies {
    commonMainApi("dev.icerock.moko:resources:0.21.2")
}

multiplatformResources {
    multiplatformResourcesPackage = "org.example.library" // required
    iosBaseLocalizationRegion = "en" // optional, default "en"
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            binaryOption("bundleVersion", "1")
            export("dev.icerock.moko:resources:0.21.2")
            export("dev.icerock.moko:graphics:0.9.0") // toUIColor here
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
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