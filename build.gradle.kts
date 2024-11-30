plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    alias(libs.plugins.serialization).apply(false)
    alias(libs.plugins.firebase.appdistribution).apply(false)
    alias(libs.plugins.composeMultiplatform).apply(false)
    alias(libs.plugins.kotlinCompose).apply(false)
    alias(libs.plugins.kotlinJvm).apply(false)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
}

buildscript {
    dependencies {
        classpath(libs.google.services)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.buildkonfig.gradle.plugin)
    }
}

dependencyLocking {
    lockAllConfigurations()
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
