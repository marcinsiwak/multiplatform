plugins {
    id("com.android.application").version("8.0.2").apply(false)
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("android").version("1.8.21").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    kotlin("plugin.serialization") version "1.8.22"
}

buildscript {

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("com.google.gms:google-services:4.3.13")
        classpath("dev.icerock.moko:resources-generator:0.23.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.5")
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:0.14.0")
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