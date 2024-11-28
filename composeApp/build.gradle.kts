import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinCompose)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("com.google.firebase.appdistribution")
    id("pl.msiwak.convention.target.config")

}

apply(from = "$rootDir/gradle/buildVariants.gradle")


kotlin {


//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        moduleName = "composeApp"
//        browser {
//            val rootDirPath = project.rootDir.path
//            val projectDirPath = project.projectDir.path
//            commonWebpackConfig {
//                outputFileName = "composeApp.js"
//                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                    static = (static ?: mutableListOf()).apply {
//                        // Serve sources to debug inside browser
//                        add(rootDirPath)
//                        add(projectDirPath)
//                    }
//                }
//            }
//        }
//        binaries.executable()
//    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
//            implementation(libs.androidx.lifecycle.viewmodel)
//            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(project(":shared-mobile"))
            implementation(project(":shared:model"))
//            projects
//            implementation(projects.shared.lib)
        }
    }
}

val versionMajor = 1
val versionMinor = 1
val versionPatch = 2
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
        isCoreLibraryDesugaringEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                file("proguard-rules.pro")
            )
        }
        debug {
            applicationIdSuffix = ".debug"
        }
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

        buildTypes {
            release {
                signingConfig = signingConfigs.getByName("release")
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

        buildTypes {
            debug {
                signingConfig = signingConfigs.getByName("debug")
            }
        }
    }

    productFlavors {
        getByName("staging") {
            applicationIdSuffix = ".staging"

            val stagingPropertiesFile = rootProject.file("androidApp/staging.properties")
            if (stagingPropertiesFile.exists()) {
                val stagingProperties = Properties()
                stagingProperties.load(FileInputStream(stagingPropertiesFile))

                buildConfigField(
                    "String",
                    "GOOGLE_AUTH_WEB_CLIENT_ID",
                    stagingProperties["GOOGLE_AUTH_WEB_CLIENT_ID"] as String
                )
            }
        }
        getByName("production") {
            val productionPropertiesFile = rootProject.file("androidApp/production.properties")
            if (productionPropertiesFile.exists()) {
                val productionProperties = Properties()
                productionProperties.load(FileInputStream(productionPropertiesFile))

                buildConfigField(
                    "String",
                    "GOOGLE_AUTH_WEB_CLIENT_ID",
                    productionProperties["GOOGLE_AUTH_WEB_CLIENT_ID"] as String
                )
            }
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
    implementation(libs.androidx.appcompat)
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.2")
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

    debugImplementation(compose.uiTooling)
}
