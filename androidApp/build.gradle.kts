import java.io.FileInputStream
import java.util.Properties
import pl.msiwak.multiplatfor.dependencies.Deps

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("android")
}

val versionMajor = 0
val versionMinor = 0
val versionPatch = 1
val versionBuild = 0
val versionCode =
    1_000_000 * versionMajor + 10_000 * versionMinor + 100 * versionPatch + versionBuild

val appVersionCode: Int = Integer.valueOf(System.getenv("BUILD_NUMBER") ?: "$versionCode")

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "pl.msiwak.athletetrack.android"
        minSdk = 27
        targetSdk = 33
        versionCode = appVersionCode
        versionName = "$versionMajor.$versionMinor.$versionPatch ($appVersionCode)"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    val releaseKeystorePropFile = rootProject.file("signing/release.properties")

    if (releaseKeystorePropFile.exists()) {
        val releaseKeystoreProp = Properties()
        releaseKeystoreProp.load(FileInputStream(releaseKeystorePropFile))

        signingConfigs {
            maybeCreate("release")
            getByName("release") {
                keyAlias = releaseKeystoreProp["keyAlias"] as String
                keyPassword = releaseKeystoreProp["keyPassword"] as String
                storeFile = rootProject.file(releaseKeystoreProp["storeFile"] as String)
                storePassword = releaseKeystoreProp["storePassword"] as String
            }
        }

        buildTypes {
            release {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android.txt"),
                    file("proguard-rules.pro")
                )
                signingConfig = signingConfigs.getByName("release")
            }
            debug {
                signingConfig = signingConfigs.getByName("debug")
            }
        }
    }

    val debugKeystorePropFile = rootProject.file("signing/debug.properties")
    val debugKeystoreProp = Properties()
    debugKeystoreProp.load(FileInputStream(debugKeystorePropFile))

    signingConfigs {
        maybeCreate("debug")
        getByName("debug") {
            keyAlias = debugKeystoreProp["keyAlias"] as String
            keyPassword = debugKeystoreProp["keyPassword"] as String
            storeFile = rootProject.file(debugKeystoreProp["storeFile"] as String)
            storePassword = debugKeystoreProp["storePassword"] as String
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

    val composeBom = platform("androidx.compose:compose-bom:2022.10.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("androidx.compose.ui:ui:${rootProject.extra["compose_version"]}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")

    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("com.google.accompanist:accompanist-navigation-material:0.25.0")

    with(Deps.Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
    }

}