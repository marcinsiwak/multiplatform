import pl.msiwak.convention.config.baseSetup
import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.serialization)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.kotlinCompose)
    id("pl.msiwak.convention.android.config")
    id("pl.msiwak.convention.target.config")
    id("pl.msiwak.convention.releaseonly.config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        baseSetup()
        framework {
            baseName = "ui-dashboard"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.domain))
            implementation(project(Modules.navigator))
            implementation(project(Modules.utils))
            implementation(project(Modules.commonResources))
            implementation(project(Modules.commonObject))
            implementation(project(Modules.uiCommonComponent))
            implementation(project(Modules.uiSummary))
            implementation(project(Modules.uiSettings))

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.compose.multiplatform.navigation)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.kotlinx.lifecycle)
            implementation(libs.kotlinx.viewModel)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.ui.dashboard"
}
