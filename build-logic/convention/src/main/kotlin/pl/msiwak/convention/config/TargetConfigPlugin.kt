package pl.msiwak.convention.config

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

class TargetConfigPlugin : Plugin<Project> {
    @OptIn(ExperimentalKotlinGradlePluginApi::class, ExperimentalWasmDsl::class)
    override fun apply(project: Project) {
        with(project.extensions.getByType<KotlinMultiplatformExtension>()) {
            androidTarget {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_17)
                }
            }
            jvmToolchain(17)
            wasmJs {
                browser {
                    val rootDirPath = project.rootDir.path
                    val projectDirPath = project.projectDir.path
                    commonWebpackConfig {
                        devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                            static = (static ?: mutableListOf()).apply {
                                // Serve sources to debug inside browser
                                add(rootDirPath)
                                add(projectDirPath)
                            }
                        }
                    }
                }
            }
            iosX64()
            iosArm64()
            iosSimulatorArm64()
        }
    }
}
