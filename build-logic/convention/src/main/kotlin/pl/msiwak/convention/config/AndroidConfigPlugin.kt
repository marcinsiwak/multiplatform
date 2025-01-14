package pl.msiwak.convention.config

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

class AndroidConfigPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val javaVersion = JavaVersion.VERSION_17

        with(project.extensions.getByType<BaseExtension>()) {
            compileSdkVersion(34)

            with(defaultConfig) {
                minSdk = 27
            }
        }

        project.kotlinExtension.jvmToolchain(
            JavaLanguageVersion.of(javaVersion.majorVersion).asInt()
        )
    }
}
