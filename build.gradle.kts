plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.serialization).apply(false)
    alias(libs.plugins.firebase.appdistribution).apply(false)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}

buildscript {
    dependencies {
        classpath(libs.google.services)
        classpath(libs.resources.generator)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.buildkonfig.gradle.plugin)
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
