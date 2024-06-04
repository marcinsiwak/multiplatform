pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        maven("https://androidx.dev/storage/compose-compiler/repository/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "athletetrack"
include(":androidApp")
include(":shared")
include(":shared:core")
include(":shared:commonResources")
include(":shared:database")
include(":shared:commonObject")
include(":shared:utils")
include(":shared:auth")
include(":shared:network")
include(":shared:data")
include(":shared:remoteConfig")
include(":shared:domain")
include(":shared:domain-impl")
include(":shared:navigator")
include(":shared:buildConfig")
include(":shared:notifications")
include(":shared:ui-welcome")
include(":shared:ui-addCategory")
include(":shared:ui-exercise")
include(":shared:ui-category")
include(":shared:ui-dashboard")
include(":shared:ui-forceUpdate")
include(":shared:ui-language")
include(":shared:ui-register")
include(":shared:ui-settings")
include(":shared:ui-summary")
include(":shared:ui-unit")
include(":shared:ui-verifyEmail")
include(":shared:ui-common-component")
