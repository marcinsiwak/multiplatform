package pl.msiwak.convention.config

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@Suppress("DEPRECATION")
class ReleaseOnlyBuildVariantPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project.extensions.getByType<LibraryAndroidComponentsExtension>()) {
            beforeVariants {
                it.enabled = it.buildType == "release"
            }
        }
    }
}
