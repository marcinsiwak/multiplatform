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
val versionPatch = 3
val versionBuild = 0
val versionCode =
    1_000_000 * versionMajor + 10_000 * versionMinor + 100 * versionPatch + versionBuild

val appVersionCode: Int = Integer.valueOf(System.getenv("BUILD_NUMBER") ?: "$versionCode")

android {
    namespace = "pl.msiwak.multiplatform.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "pl.msiwak.athletetrack.android"
        minSdk = 27
        targetSdk = 34
        versionCode = appVersionCode
        versionName = "$versionMajor.$versionMinor.$versionPatch ($appVersionCode)"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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

    }

    val debugKeystorePropFile = rootProject.file("signing/debug.properties")
    if (debugKeystorePropFile.exists()) {
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

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                file("proguard-rules.pro")
            )
            signingConfig = signingConfigs.getByName("release")

            val releasePropertiesFile = rootProject.file("androidApp/release.properties")
            val releaseProperties = Properties()
            releaseProperties.load(FileInputStream(releasePropertiesFile))

            buildConfigField(
                "String",
                "GOOGLE_AUTH_WEB_CLIENT_ID",
                releaseProperties["GOOGLE_AUTH_WEB_CLIENT_ID"] as String
            )
        }
        debug {

            val debugPropertiesFile = rootProject.file("androidApp/debug.properties")
            val debugProperties = Properties()
            debugProperties.load(FileInputStream(debugPropertiesFile))

            signingConfig = signingConfigs.getByName("debug")
            buildConfigField(
                "String",
                "GOOGLE_AUTH_WEB_CLIENT_ID",
                debugProperties["GOOGLE_AUTH_WEB_CLIENT_ID"] as String
            )
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":shared:core"))
    implementation("androidx.appcompat:appcompat:1.6.1")

    val composeBom = platform("androidx.compose:compose-bom:2023.10.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui")

    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")

    implementation("androidx.navigation:navigation-compose:2.7.3")
    implementation("com.google.accompanist:accompanist-navigation-material:0.25.0")

    with(Deps.Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
    }
    with(Deps.Google) {
        api(andorid_play_services_auth)
    }

}