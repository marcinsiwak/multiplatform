package pl.msiwak.convention.config

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class TargetConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project.extensions.getByType<KotlinMultiplatformExtension>()) {
//            androidTarget {
//                compilations.all {
//                    kotlinOptions {
//                        jvmTarget = "17"
//                    }
//                }
//            }
//            jvmToolchain(17)
//            iosX64()
//            iosArm64()
//            iosSimulatorArm64()
        }
    }
}
