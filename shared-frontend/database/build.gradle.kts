import org.jetbrains.kotlin.gradle.tasks.KotlinCompileCommon
import pl.msiwak.convention.config.baseSetup
import pl.msiwak.multiplatform.dependencies.Modules

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sqlDelight)
    id("pl.msiwak.convention.releaseonly.config")
    id("pl.msiwak.convention.android.config")
}

kotlin {
    androidTarget()
    jvmToolchain(17)

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    metadata {
        compilations.all {
            val compilationName = name
            compileTaskProvider.configure {
                if (this is KotlinCompileCommon) {
                    moduleName = "${project.group}:${project.name}_$compilationName"
                }
            }
        }
    }

    cocoapods {
        baseSetup()
        framework {
            baseName = "database"
            linkerOpts += "-lsqlite3"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(Modules.commonObject))

            implementation(libs.sqlDelight.coroutines)
            implementation(libs.kotlinx.dateTime)
            implementation(libs.kotlinx.serialization)
        }
        androidMain.dependencies {
            implementation(libs.sqlDelight.android)
        }

        iosMain.dependencies {
            implementation(libs.sqlDelight.ios)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.database"
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("pl.msiwak.multiplatform.shared.database.sql")
        }
    }
    linkSqlite.set(true)
}
