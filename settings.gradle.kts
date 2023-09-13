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
