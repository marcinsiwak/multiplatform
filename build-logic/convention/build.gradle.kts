plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("compose") {
            id = "pl.msiwak.convention.compose"
            implementationClass = "pl.msiwak.convention.compose.ComposePlugin"
        }
        register("AndroidConfigPlugin") {
            id = "pl.msiwak.convention.android.config"
            implementationClass = "pl.msiwak.convention.config.AndroidConfigPlugin"
        }
    }
}

dependencies {
    implementation(gradleApi())
    implementation(libs.gradle.ksp)
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.android)
}
