plugins {
//    id(libs.plugins.kotlinAndroid.get().pluginId) apply false
//    id(libs.plugins.kotlinCocoapods.get().pluginId) apply false
    `java-gradle-plugin`
    `kotlin-dsl`
//    id(libs.plugins.androidApplication.get().pluginId).apply(false)
//    id(libs.plugins.androidLibrary.get().pluginId).apply(false)
//    id(libs.plugins.kotlinAndroid.get().pluginId).apply(false)
//    id(libs.plugins.kotlinMultiplatform.get().pluginId).apply(false)
//    id(libs.plugins.kotlinCocoapods.get().pluginId).apply(false)

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
    }
}

dependencies {
    implementation(gradleApi())
    implementation(libs.gradle.ksp)
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.android)
}
