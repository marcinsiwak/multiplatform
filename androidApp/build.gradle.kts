import pl.msiwak.multiplatfor.dependencies.Deps
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    kotlin("android")
}

val versionMajor = 0
val versionMinor = 0
val versionPatch = 3
val versionBuild = 0
val versionCode =
    1_000_000 * versionMajor + 10_000 * versionMinor + 100 * versionPatch + versionBuild

val appVersionCode: Int = Integer.valueOf(System.getenv("BUILD_NUMBER") ?: "$versionCode")

apply(from = "$rootDir/gradle/buildVariants.gradle")

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
                storeFile = rootProject.file("signing/release.jks")
                storePassword = releaseKeystoreProp["storePassword"] as String
            }
        }
    } else {
        signingConfigs {
            maybeCreate("release")
            getByName("release") {
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASSWORD")
                storeFile = rootProject.file("signing/release.jks")
                storePassword = System.getenv("KEY_STORE_PASSWORD")
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
                storeFile = rootProject.file("signing/debug.jks")
                storePassword = debugKeystoreProp["storePassword"] as String
            }
        }
    } else {
        signingConfigs {
            maybeCreate("debug")
            getByName("debug") {
                keyAlias = System.getenv("KEY_ALIAS_DEBUG")
                keyPassword = System.getenv("KEY_PASSWORD_DEBUG")
                storeFile = rootProject.file("signing/debug.jks")
                storePassword = System.getenv("KEY_STORE_PASSWORD_DEBUG")
            }
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                file("proguard-rules.pro")
            )
            signingConfig = signingConfigs.getByName("release")

            val releasePropertiesFile = rootProject.file("androidApp/production.properties")
            val releaseProperties = Properties()
            releaseProperties.load(FileInputStream(releasePropertiesFile))

            buildConfigField(
                "String",
                "GOOGLE_AUTH_WEB_CLIENT_ID",
                releaseProperties["GOOGLE_AUTH_WEB_CLIENT_ID"] as String
            )
        }
        debug {
            applicationIdSuffix = ".debug"

            val stagingPropertiesFile = rootProject.file("androidApp/staging.properties")
            val stagingProperties = Properties()
            stagingProperties.load(FileInputStream(stagingPropertiesFile))

            signingConfig = signingConfigs.getByName("debug")
            buildConfigField(
                "String",
                "GOOGLE_AUTH_WEB_CLIENT_ID",
                stagingProperties["GOOGLE_AUTH_WEB_CLIENT_ID"] as String
            )
        }
    }

    productFlavors {
        getByName("staging") {
            applicationIdSuffix = ".staging"
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
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation("androidx.compose.ui:ui-tooling:1.5.4")
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
        api(andorid_play_services_ads)
    }
}
