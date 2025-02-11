plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    alias(libs.plugins.kotlinCocoapods).apply(false)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.toString()))
    }
}
