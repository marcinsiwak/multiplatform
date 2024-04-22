import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.plugin.cocoapods.KotlinCocoapodsPlugin
import java.io.FileInputStream
import java.util.Properties
import java.util.regex.Pattern

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("com.codingfeline.buildkonfig")
    id("pl.msiwak.convention.android.config")
    id("pl.msiwak.convention.target.config")
}

apply(from = "$rootDir/gradle/buildVariants.gradle")

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "buildConfig"
        }

        xcodeConfigurationToNativeBuildType["productionRelease"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.RELEASE
        xcodeConfigurationToNativeBuildType["productionDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
        xcodeConfigurationToNativeBuildType["stagingDebug"] =
            org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType.DEBUG
    }

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

buildkonfig {
    packageName = "pl.msiwak.multiplatform.shared.buildConfig"

    val productionPropertiesFile = rootProject.file("production.properties")
    val productionProperties = Properties()
    productionProperties.load(FileInputStream(productionPropertiesFile))

    defaultConfigs {
        buildConfigField(STRING, "BUILD_FLAVOUR", "default")
        buildConfigField(STRING, "BASE_URL", productionProperties["BASE_URL"] as String)
        buildConfigField(BOOLEAN, "IsDebug", "false")
    }

    targetConfigs {
        create("android") {
            buildConfigField(STRING, "BUILD_FLAVOUR", "productionReleaseAndroid")
            buildConfigField(STRING, "BASE_URL", productionProperties["BASE_URL"] as String)
            buildConfigField(BOOLEAN, "IsDebug", "false")
        }

        create("ios") {
            buildConfigField(STRING, "BUILD_FLAVOUR", "productionReleaseIos")
            buildConfigField(STRING, "BASE_URL", productionProperties["BASE_URL"] as String)
            buildConfigField(BOOLEAN, "IsDebug", "false")
        }
    }

    targetConfigs("productionDebug") {
        create("android") {
            buildConfigField(STRING, "BUILD_FLAVOUR", "productionDebugAndroid")
            buildConfigField(STRING, "BASE_URL", productionProperties["BASE_URL"] as String)
            buildConfigField(BOOLEAN, "IsDebug", "true")
        }
        create("ios") {
            buildConfigField(STRING, "BUILD_FLAVOUR", "productionDebugIos")
            buildConfigField(STRING, "BASE_URL", productionProperties["BASE_URL"] as String)
            buildConfigField(BOOLEAN, "IsDebug", "true")
        }
    }

    targetConfigs("stagingDebug") {
        val stagingPropertiesFile = rootProject.file("staging.properties")
        val stagingProperties = Properties()
        stagingProperties.load(FileInputStream(stagingPropertiesFile))

        create("android") {
            buildConfigField(STRING, "BUILD_FLAVOUR", "stagingDebugAndroid")
            buildConfigField(STRING, "BASE_URL", stagingProperties["BASE_URL"] as String)
            buildConfigField(BOOLEAN, "IsDebug", "true")
        }
        create("ios") {
            buildConfigField(STRING, "BUILD_FLAVOUR", "stagingDebugIos")
            buildConfigField(STRING, "BASE_URL", stagingProperties["BASE_URL"] as String)
            buildConfigField(BOOLEAN, "IsDebug", "true")
        }
    }
}

fun getCurrentVariant(): String {
    val gradle: Gradle = gradle
    val tskReqStr: String = gradle.startParameter.taskRequests.toString()

    val pattern: Pattern = if (tskReqStr.contains("assemble")) {
        Pattern.compile("assemble(\\w+)(Release|Debug)")
    } else {
        Pattern.compile("generate(\\w+)(Release|Debug)")
    }

    val matcher = pattern.matcher(tskReqStr)

    return if (matcher.find()) {
        matcher.group(2)?.lowercase() ?: ""
    } else {
        println("NO MATCH FOUND")
        ""
    }
}

fun getCurrentFlavor(): String {
    val gradle: Gradle = gradle
    val tskReqStr: String = gradle.startParameter.taskRequests.toString()

    val pattern: Pattern = when {
        tskReqStr.contains("assemble") -> Pattern.compile("assemble(\\w+)(Release|Debug)")
        tskReqStr.contains("bundle") -> Pattern.compile("bundle(\\w+)(Release|Debug)")
        else -> Pattern.compile("generate(\\w+)(Release|Debug)")
    }

    val matcher = pattern.matcher(tskReqStr)

    return if (matcher.find()) {
        matcher.group(1)?.lowercase() ?: ""
    } else {
        println("NO MATCH FOUND")
        ""
    }
}

tasks.create("setupBuildkonfigIos") {
    doLast {
        val flavour = project.findProperty("kmmflavour") as String?
        project.extra["buildkonfig.flavor"] = flavour
    }
}

tasks.create("setupBuildkonfig") {
    val variant = getCurrentVariant()
    val flavor = getCurrentFlavor()
    val kmmFlavor = flavor.plus(variant.capitalized())

    if (kmmFlavor.isEmpty()) {
        val iosVariant = project.findProperty(KotlinCocoapodsPlugin.CONFIGURATION_PROPERTY)

        project.setProperty("buildkonfig.flavor", iosVariant)
    } else {
        project.setProperty("buildkonfig.flavor", kmmFlavor)
    }
}

tasks.preBuild.dependsOn("setupBuildkonfig")

android {
    namespace = "pl.msiwak.multiplatform.buildconfig"
}
