import pl.msiwak.convention.config.baseSetup
import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("pl.msiwak.convention.target.config")
    id("pl.msiwak.convention.releaseonly.config")
    id("pl.msiwak.convention.android.config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        baseSetup()
        framework {
            baseName = "domain"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.commonObject))
            implementation(project(Modules.commonResources))

            implementation(libs.kotlinx.coroutines)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.domain"
}
