import pl.msiwak.convention.config.baseSetup
import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("pl.msiwak.convention.target.config")
    id("pl.msiwak.convention.android.config")
}

apply(from = "$rootDir/gradle/buildVariants.gradle")

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        baseSetup()
        framework {
            baseName = "domain-impl"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.domain))
            implementation(project(Modules.data))
            implementation(project(Modules.commonObject))
            implementation(project(Modules.commonResources))
            implementation(project(Modules.utils))
            implementation(project(Modules.notifications))
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.domainimpl"
}
