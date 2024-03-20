plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("dev.icerock.mobile.multiplatform-resources")
    alias(libs.plugins.composeMultiplatform)
}

multiplatformResources {
    multiplatformResourcesPackage = "pl.msiwak.multiplatform.commonResources" // required
    iosBaseLocalizationRegion = "en" // optional, default "en"
    multiplatformResourcesClassName = "SR"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {

    androidTarget() {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    jvmToolchain(17)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "CommonResources for the Shared Module"
        homepage = "https://github.com/marcinsiwak/multiplatform"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "commonResources"

            export(libs.moko.resources)
            export(libs.moko.resources.compose)
            export(libs.moko.graphics)
        }
        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
    }

    sourceSets {
        getByName("androidMain").dependsOn(commonMain.get())
        getByName("iosArm64Main").dependsOn(commonMain.get())
        getByName("iosX64Main").dependsOn(commonMain.get())
        getByName("iosSimulatorArm64Main").dependsOn(commonMain.get())

        commonMain.dependencies {
            api(libs.moko.resources)
            api(libs.moko.resources.compose)
            api(libs.moko.graphics)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
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
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}
