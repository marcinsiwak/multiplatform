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
include(":shared-mobile")
include(":shared-mobile:commonResources")
include(":shared-mobile:database")
include(":shared-mobile:commonObject")
include(":shared-mobile:utils")
include(":shared-mobile:auth")
include(":shared-mobile:network")
include(":shared-mobile:data")
include(":shared-mobile:remoteConfig")
include(":shared-mobile:domain")
include(":shared-mobile:domain-impl")
include(":shared-mobile:navigator")
include(":shared-mobile:buildConfig")
include(":shared-mobile:notifications")
include(":shared-mobile:ui-welcome")
include(":shared-mobile:ui-addCategory")
include(":shared-mobile:ui-addExercise")
include(":shared-mobile:ui-category")
include(":shared-mobile:ui-dashboard")
include(":shared-mobile:ui-forceUpdate")
include(":shared-mobile:ui-language")
include(":shared-mobile:ui-register")
include(":shared-mobile:ui-settings")
include(":shared-mobile:ui-summary")
include(":shared-mobile:ui-unit")
include(":shared-mobile:ui-verifyEmail")
include(":shared-mobile:ui-common-component")
include(":shared-mobile:ui-terms")