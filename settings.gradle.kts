pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
//
//plugins {
//    id("org.gradle.toolchains.foojay-resolver-convention") version("0.4.0")
//}

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
include(":shared:notifications")
