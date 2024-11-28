val kotlin_version: String by project
val logback_version: String by project
val postgresql_driver_version: String by project
val exposed_version: String by project
val koin_ktor_version: String by project
val ktor_version: String by project
val flyway_version: String by project

plugins {
    application
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.12"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20-Beta2"
    id("org.flywaydb.flyway") version "8.5.4"
}

group = "msiwak.pl"
version = "1.0.0"

application {
    mainClass.set("pl.msiwak.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    // Ktor Core
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")

    // Content Negotiation and Serialization
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")

    // Auth JWT
    implementation("io.ktor:ktor-server-auth-jvm")
    implementation("io.ktor:ktor-server-auth-jwt-jvm")
    implementation("io.ktor:ktor-server-host-common-jvm")

    // Ktor html
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")

    // Firebase
    implementation("com.google.firebase:firebase-admin:9.3.0")

    // Postgres SQL
    implementation("org.postgresql:postgresql:$postgresql_driver_version")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposed_version")

    // Koin for Ktor
    implementation("io.insert-koin:koin-ktor:$koin_ktor_version")
    // SLF4J Logger
    implementation("io.insert-koin:koin-logger-slf4j:$koin_ktor_version")

    // Flyaway - db migrations
    implementation("org.flywaydb:flyway-core:$flyway_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    //implementation(libs.local.model)

}
