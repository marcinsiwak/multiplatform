import pl.msiwak.convention.config.baseSetup

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
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
            baseName = "commonResources"
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.ui)
            api(compose.components.resources)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

compose.resources {
    publicResClass = true
}

android {
    namespace = "pl.msiwak.multiplatform.commonResources"
}
