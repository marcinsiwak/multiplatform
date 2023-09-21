import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN
import java.io.FileInputStream
import java.util.Properties
import pl.msiwak.multiplatfor.dependencies.Deps
import pl.msiwak.multiplatfor.dependencies.Modules

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.22"
    id("com.codingfeline.buildkonfig")
    id("dev.icerock.mobile.multiplatform-resources")
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

            compilation.kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")

//            binaryOption("bundleVersion", "1")
            export(Deps.MokoResources.resources)
            export(Deps.MokoResources.graphics)

            export(project(Modules.commonObject))
            export(project(Modules.commonResources))
            export(project(Modules.database))
            export(project(Modules.utils))
            export(project(Modules.auth))
            export(project(Modules.ui))
            export(project(Modules.injector))
        }

        pod("FirebaseCore", linkOnly = true)
        pod("FirebaseAuth", linkOnly = true)
        pod("FirebaseRemoteConfig", linkOnly = true)
        pod("FirebaseCrashlytics", linkOnly = true)
        pod("GoogleSignIn", linkOnly = true)

    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.commonObject))
                api(project(Modules.commonResources))
                api(project(Modules.database))
                api(project(Modules.utils))
                api(project(Modules.auth))
                api(project(Modules.ui))
                api(project(Modules.injector))
            }
        }

        val androidMain by getting {
            dependencies {
                dependsOn(commonMain)
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

buildkonfig {
    packageName = "pl.msiwak.multiplatform.shared.core"

    val releasePropertiesFile = rootProject.file("release.properties")
    val releaseProperties = Properties()
    releaseProperties.load(FileInputStream(releasePropertiesFile))

    defaultConfigs {
        buildConfigField(STRING, "BASE_URL", releaseProperties["BASE_URL"] as String)
        buildConfigField(BOOLEAN, "IsDebug", "false")
    }
    targetConfigs {

        create("android") {
            buildConfigField(STRING, "BASE_URL", releaseProperties["BASE_URL"] as String)
            buildConfigField(BOOLEAN, "IsDebug", "false")
        }

        create("ios") {
            buildConfigField(STRING, "BASE_URL", releaseProperties["BASE_URL"] as String)
            buildConfigField(BOOLEAN, "IsDebug", "false")
        }
    }
    targetConfigs("dev") {

        val debugPropertiesFile = rootProject.file("debug.properties")
        val debugProperties = Properties()
        debugProperties.load(FileInputStream(debugPropertiesFile))

        create("android") {
            buildConfigField(STRING, "BASE_URL", debugProperties["BASE_URL"] as String)
            buildConfigField(BOOLEAN, "IsDebug", "true")
        }
        create("ios") {
            buildConfigField(STRING, "BASE_URL", debugProperties["BASE_URL"] as String)
            buildConfigField(BOOLEAN, "IsDebug", "true")
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
