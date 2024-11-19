dependencyResolutionManagement {
    versionCatalogs {
        create("sharedLibs") {
            from(files("../gradle/sharedLibs.versions.toml"))
        }
    }
}

includeBuild("../shared")

rootProject.name = "pl.msiwak.athletetrack-api-kotlin"
