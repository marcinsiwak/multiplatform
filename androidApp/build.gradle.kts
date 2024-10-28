import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinCompose)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("com.google.firebase.appdistribution")
}

val versionMajor = 1
val versionMinor = 1
val versionPatch = 1
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
        val firebaseServiceCredentialsFile =
            rootProject.file("androidApp/sportplatform-b5318-816058b49361.json")

        if (firebaseServiceCredentialsFile.exists()) {
            configure<com.google.firebase.appdistribution.gradle.AppDistributionExtension> {
                artifactType = "APK"
                groups = "main"
                releaseNotes = "Version for tests"
                serviceCredentialsFile = firebaseServiceCredentialsFile.path
            }
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
//        // todo remove after final versions released kotlin/compose-multiplatform
        freeCompilerArgs = listOf(
            "-Xallow-jvm-ir-dependencies",
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true"
        )
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

            val productionPropertiesFile = rootProject.file("androidApp/production.properties")
            val productionProperties = Properties()
            productionProperties.load(FileInputStream(productionPropertiesFile))

            buildConfigField(
                "String",
                "GOOGLE_AUTH_WEB_CLIENT_ID",
                productionProperties["GOOGLE_AUTH_WEB_CLIENT_ID"] as String
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

    applicationVariants.all {
        val isProduction = mergedFlavor.applicationIdSuffix.isNullOrBlank()
        val isStaging = mergedFlavor.applicationIdSuffix == ".staging"
        val buildName = buildType.name

        val prodDebug = isProduction && buildName == "debug"
        val stagingDebug = isStaging && buildName == "debug"

        val label = when {
            prodDebug -> "(PD) Athlete track"
            stagingDebug -> "(SD) Athlete track"
            else -> "Athlete track"
        }
        println("label: $label")
        mergedFlavor.manifestPlaceholders["applicationLabel"] = label
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.appcompat)

    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.androidx.core.splashscreen)

    implementation(libs.kotlinx.lifecycle.ktx)
    implementation(libs.kotlinx.viewModel.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.navigation.material)

    implementation(libs.components.resources)

    api(libs.firebase.common)
    api(libs.firebase.auth)
    api(libs.firebase.config)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose.android)

    implementation(libs.google.android.playservices.ads)
}
