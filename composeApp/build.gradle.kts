import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import pl.msiwak.multiplatform.dependencies.Modules
import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinCompose)
    alias(libs.plugins.serialization)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("com.google.firebase.appdistribution")
}

apply(from = "$rootDir/gradle/buildVariants.gradle")

@OptIn(ExperimentalWasmDsl::class)
kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    jvmToolchain(17)

    iosX64()
    iosArm64()
    iosSimulatorArm64()
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    port = 3000
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

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
            implementation(compose.components.uiToolingPreview)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(libs.kotlinx.serialization)
            implementation(project(Modules.sharedModel))
            implementation(project(Modules.sharedFrontend))

            implementation(libs.kotlinx.lifecycle)
            implementation(libs.kotlinx.viewModel)
            implementation(libs.compose.multiplatform.navigation)
        }
    }
}

val versionMajor = 1
val versionMinor = 1
val versionPatch = 4
val versionBuild = 2
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
            rootProject.file("composeApp/sportplatform-distribution.json")

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
        }
        getByName("production")
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

    coreLibraryDesugaring(libs.desugar.jdk.libs)
}
