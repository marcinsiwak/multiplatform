plugins {
    alias(sharedLibs.plugins.kotlinMultiplatform)
    alias(sharedLibs.plugins.serialization)
}

group = "pl.msiwak.shared"
version = "1.0.0"

kotlin {
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(sharedLibs.kotlinx.serialization)
                implementation(sharedLibs.kotlinx.dateTime)
            }
        }
    }
}
