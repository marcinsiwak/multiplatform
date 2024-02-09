plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    kotlin("plugin.serialization") version "1.8.22"
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("com.google.firebase.appdistribution") version "4.0.1" apply false
}

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("dev.icerock.moko:resources-generator:0.23.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.14.0")
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(from = "$rootDir/extras/detekt.gradle")

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        filter {
            exclude { element ->
                element.file.path.contains("generated/")
            }
            exclude { element ->
                element.file.path.contains("buildkonfig/")
            }
            exclude { element ->
                element.file.path.contains("Buildkonfig/")
            }
            include("**/kotlin/**")
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
