pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
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
include(":shared:injector")
include(":shared:ui")
include(":shared:buildConfig")
