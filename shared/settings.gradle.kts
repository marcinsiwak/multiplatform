pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("sharedLibs") {
            from(files("../gradle/sharedLibs.versions.toml"))
        }
    }
}

include(
    "model"
)

rootProject.name = "shared"
