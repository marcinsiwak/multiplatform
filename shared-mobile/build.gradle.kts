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
}

apply(from = "$rootDir/gradle/buildVariants.gradle")

kotlin {
    cocoapods {
        summary = "Main Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "16.2"

        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = "shared-mobile"
            if (System.getenv("XCODE_VERSION_MAJOR") == "1500") {
                linkerOpts += "-ld_classic"
            }
            isStatic = true
            compilation.kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")
            compilation.project.setProperty("buildkonfig.flavor", "productionDebug")
        }

        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG

        pod("FirebaseCore", linkOnly = true)
        pod("FirebaseAuth", linkOnly = true)
        pod("FirebaseRemoteConfig", linkOnly = true)
        pod("FirebaseCrashlytics", linkOnly = true)
        pod("GoogleSignIn", linkOnly = true)
        pod("FirebaseMessaging", linkOnly = true)
        pod("Google-Mobile-Ads-SDK", moduleName = "GoogleMobileAds", linkOnly = true)
    }

    sourceSets {
        androidMain.dependencies {
            api(project(Modules.database))
        }

        iosMain.dependencies {
            api(project(Modules.database))
        }

        commonMain.dependencies {
            api(project(Modules.commonResources))
            api(project(Modules.commonObject))
            api(project(Modules.utils))
            api(project(Modules.auth))
            api(project(Modules.network))
            api(project(Modules.data))
            api(project(Modules.remoteConfig))
            api(project(Modules.domain))
            api(project(Modules.domainImpl))
            api(project(Modules.navigator))
            api(project(Modules.uiWelcome))
            api(project(Modules.uiAddCategory))
            api(project(Modules.uiAddExercise))
            api(project(Modules.uiCategory))
            api(project(Modules.uiDashboard))
            api(project(Modules.uiForceUpdate))
            api(project(Modules.uiLanguage))
            api(project(Modules.uiRegister))
            api(project(Modules.uiSettings))
            api(project(Modules.uiSummary))
            api(project(Modules.uiUnit))
            api(project(Modules.uiVerifyEmail))
            api(project(Modules.buildConfig))
            api(project(Modules.notifications))
            api(project(Modules.uiCommonComponent))
            api(project(Modules.uiTerms))

            implementation(libs.koin.core)
            implementation(libs.koin.test)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(libs.kotlinx.lifecycle)
            implementation(libs.kotlinx.viewModel)
            implementation(libs.compose.multiplatform.navigation)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
        }

        wasmJsMain.dependencies {
            api(project(Modules.databaseWasm))
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

// android {
//     namespace = "pl.msiwak.multiplatform.shared"
//     sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
// }

android {
    namespace = "pl.msiwak.multiplatform.shared"
}
