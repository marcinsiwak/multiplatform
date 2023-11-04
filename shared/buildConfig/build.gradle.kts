import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN
import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.codingfeline.buildkonfig")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget() {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
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
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "buildConfig"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

buildkonfig {
    packageName = "pl.msiwak.multiplatform.shared.buildConfig"

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
    targetConfigs("debug") {

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
    namespace = "pl.msiwak.multiplatform.buildconfig"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }

}