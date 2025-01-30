rootProject.name = "athletetrack"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":backendApp")
include(":shared")
include(":shared:model")
include(":shared:utils")
include(":shared-frontend")
include(":shared-frontend:commonResources")
include(":shared-frontend:database")
include(":shared-frontend:database-wasm")
include(":shared-frontend:commonObject")
include(":shared-frontend:utils")
include(":shared-frontend:auth")
include(":shared-frontend:network")
include(":shared-frontend:data")
include(":shared-frontend:remoteConfig")
include(":shared-frontend:domain")
include(":shared-frontend:domain-impl")
include(":shared-frontend:navigator")
include(":shared-frontend:buildConfig")
include(":shared-frontend:notifications")
include(":shared-frontend:ui-welcome")
include(":shared-frontend:ui-addCategory")
include(":shared-frontend:ui-addExercise")
include(":shared-frontend:ui-category")
include(":shared-frontend:ui-dashboard")
include(":shared-frontend:ui-forceUpdate")
include(":shared-frontend:ui-language")
include(":shared-frontend:ui-register")
include(":shared-frontend:ui-settings")
include(":shared-frontend:ui-summary")
include(":shared-frontend:ui-unit")
include(":shared-frontend:ui-verifyEmail")
include(":shared-frontend:ui-common-component")
include(":shared-frontend:ui-terms")
include(":shared-frontend:ui-admin-panel")
include(":shared-frontend:store")
