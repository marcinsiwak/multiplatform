import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
    id("org.flywaydb.flyway") version "8.5.4"
    application
}

group = "msiwak.pl"
version = "1.0.0"

application {
    mainClass.set("pl.msiwak.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(project(Modules.sharedModel))

    // Ktor Core
    implementation(libs.ktor.server.core.jvm)
    implementation(libs.ktor.server.netty.jvm)
    implementation(libs.ktor.server.html.builder)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.statusPages)

    // Content Negotiation and Serialization
    implementation(libs.ktor.server.content.negotiation.jvm)
    implementation(libs.ktor.serialization.kotlinx.json.jvm)

    // Auth JWT
    implementation(libs.ktor.server.auth.jvm)
    implementation(libs.ktor.server.auth.jwt.jvm)
    implementation(libs.ktor.server.host.common.jvm)

    // Ktor html
    implementation(libs.ktor.server.html.builder)

    implementation(libs.ktor.server.config.yaml)

    // Firebase
    implementation(libs.firebase.admin)

    // Postgres SQL
    implementation(libs.postgresql)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.kotlin.datetime)

    // Koin for Ktor
    implementation(libs.koin.ktor)
    // SLF4J Logger
    implementation(libs.koin.logger.slf4j)

    // Flyaway - db migrations
    implementation(libs.flyway.core)
    implementation(libs.logback.classic)
    testImplementation(libs.kotlin.test.junit)
}
