plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    alias(libs.plugins.kotlinCocoapods)
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("AndroidConfigPlugin") {
            id = "pl.msiwak.convention.android.config"
            implementationClass = "pl.msiwak.convention.config.AndroidConfigPlugin"
        }
        register("TargetConfigPlugin") {
            id = "pl.msiwak.convention.target.config"
            implementationClass = "pl.msiwak.convention.config.TargetConfigPlugin"
        }
        register("ReleaseOnlyBuildVariantPlugin ") {
            id = "pl.msiwak.convention.releaseonly.config"
            implementationClass = "pl.msiwak.convention.config.ReleaseOnlyBuildVariantPlugin"
        }
    }
}

dependencies {
    implementation(gradleApi())
    implementation(libs.gradle.ksp)
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.android)
}
