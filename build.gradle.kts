buildscript {
    val compose_version by extra("1.1.0-beta01")
    val kotlin_version by extra("1.7.20")
    val sqlDelightVersion = "1.5.4"
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()

    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("com.google.gms:google-services:4.3.13")
        classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
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