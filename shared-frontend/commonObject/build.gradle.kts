import pl.msiwak.convention.config.baseSetup
import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinCompose)
    id("pl.msiwak.convention.target.config")
    id("pl.msiwak.convention.releaseonly.config")
    id("pl.msiwak.convention.android.config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        baseSetup()
        framework {
            baseName = "commonObject"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.commonResources))
            implementation(project(Modules.sharedModel))

            api(libs.kotlinx.dateTime)
            implementation(libs.kotlinx.serialization)

        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.commonObject"
}
