plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    id("pl.msiwak.convention.target.config")
    id("pl.msiwak.convention.android.config")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        summary = "CommonResources for the Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "commonResources"
        }
        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
    }

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.ui)
            api(compose.components.resources)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

// After executing the "generateComposeResClass" task, we replace the internal stuff by public.
tasks.named("generateComposeResClass") {
    doLast {
        val dirName = buildString {
            val group = project.group.toString()
                .lowercase()
                .replace('.', '/')
                .replace('-', '_')
            append(group)
            if (group.isNotEmpty()) {
                append("/")
            }
            append(project.name.lowercase())
        }
        val dir = project.layout.buildDirectory
            .dir("generated/compose/resourceGenerator/kotlin/$dirName/generated/resources")
            .get()
            .asFile
        File(dir, "Res.kt").also {
            if (!it.exists()) {
                return@also
            }
            val content = it.readText()
            val updatedContent = content.replace("internal object Res {", "object Res {")
            it.writeText(updatedContent)
        }
        listOf("Drawable0.kt", "String0.kt").forEach { filename ->
            File(dir, filename).also { file ->
                if (!file.exists()) {
                    return@also
                }
                val content = file.readText()
                val updatedContent = content.replace("internal val Res", "val Res")
                file.writeText(updatedContent)
            }
        }
    }
}

android {
    namespace = "pl.msiwak.multiplatform.commonResources"
}
