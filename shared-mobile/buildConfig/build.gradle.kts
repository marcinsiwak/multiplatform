import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.BOOLEAN
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.codingfeline.buildkonfig.gradle.TargetConfigDsl
import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.plugin.cocoapods.KotlinCocoapodsPlugin
import java.io.FileInputStream
import java.util.*
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
    val productionProperties = Properties().apply {
        load(FileInputStream(productionPropertiesFile))
    }
    val productionBaseUrl = "https://msiwak-api.pl"
    val stagingBaseUrl = "http://192.168.0.13:8080"

    if (productionPropertiesFile.exists()) {
        defaultConfigs {
            setupProductionFirebase()
            buildConfigField(STRING, "BUILD_FLAVOUR", "default")
            buildConfigField(STRING, "BASE_URL", productionBaseUrl)
            buildConfigField(BOOLEAN, "IsDebug", "false")
            buildConfigField(STRING, "FIREBASE_KEY", productionProperties["FIREBASE_KEY"] as String)
            buildConfigField(STRING, "FIREBASE_CLIENT_ID", productionProperties["ANDROID_FIREBASE_CLIENT_ID"] as String)
        }

        targetConfigs {
            android {
                buildConfigField(STRING, "BUILD_FLAVOUR", "productionReleaseAndroid")
                buildConfigField(STRING, "BASE_URL", productionBaseUrl)
                buildConfigField(BOOLEAN, "IsDebug", "false")
                buildConfigField(STRING, "FIREBASE_KEY", productionProperties["FIREBASE_KEY"] as String)
                buildConfigField(
                    STRING,
                    "FIREBASE_CLIENT_ID",
                    productionProperties["ANDROID_FIREBASE_CLIENT_ID"] as String
                )
            }

            ios {
                buildConfigField(STRING, "BUILD_FLAVOUR", "productionReleaseIos")
                buildConfigField(STRING, "BASE_URL", productionBaseUrl)
                buildConfigField(BOOLEAN, "IsDebug", "false")
                buildConfigField(STRING, "FIREBASE_KEY", productionProperties["FIREBASE_KEY"] as String)
                buildConfigField(
                    STRING,
                    "FIREBASE_CLIENT_ID",
                    productionProperties["ANDROID_FIREBASE_CLIENT_ID"] as String
                )
            }

            wasmJS {
                buildConfigField(STRING, "BUILD_FLAVOUR", "productionReleaseWasm")
                buildConfigField(STRING, "BASE_URL", productionBaseUrl)
                buildConfigField(BOOLEAN, "IsDebug", "false")
                buildConfigField(STRING, "FIREBASE_KEY", productionProperties["FIREBASE_KEY"] as String)
                buildConfigField(
                    STRING,
                    "FIREBASE_CLIENT_ID",
                    productionProperties["WEBAPP_FIREBASE_CLIENT_ID"] as String
                )
            }
        }

        targetConfigs("productionDebug") {
            android {
                buildConfigField(STRING, "BUILD_FLAVOUR", "productionDebugAndroid")
                buildConfigField(STRING, "BASE_URL", productionBaseUrl)
                buildConfigField(BOOLEAN, "IsDebug", "true")
                buildConfigField(STRING, "FIREBASE_KEY", productionProperties["FIREBASE_KEY"] as String)
                buildConfigField(
                    STRING,
                    "FIREBASE_CLIENT_ID",
                    productionProperties["ANDROID_FIREBASE_CLIENT_ID"] as String
                )
            }

            ios {
                buildConfigField(STRING, "BUILD_FLAVOUR", "productionDebugIos")
                buildConfigField(STRING, "BASE_URL", productionBaseUrl)
                buildConfigField(BOOLEAN, "IsDebug", "true")
                buildConfigField(STRING, "FIREBASE_KEY", productionProperties["FIREBASE_KEY"] as String)
                buildConfigField(
                    STRING,
                    "FIREBASE_CLIENT_ID",
                    productionProperties["ANDROID_FIREBASE_CLIENT_ID"] as String
                )
            }

            wasmJS {
                buildConfigField(STRING, "BUILD_FLAVOUR", "productionDebugWasm")
                buildConfigField(STRING, "BASE_URL", productionBaseUrl)
                buildConfigField(BOOLEAN, "IsDebug", "true")
                buildConfigField(STRING, "FIREBASE_KEY", productionProperties["FIREBASE_KEY"] as String)
                buildConfigField(
                    STRING,
                    "FIREBASE_CLIENT_ID",
                    productionProperties["WEBAPP_FIREBASE_CLIENT_ID"] as String
                )
            }
        }
    }

    targetConfigs("stagingDebug") {
        val stagingPropertiesFile = rootProject.file("staging.properties")
        if (stagingPropertiesFile.exists()) {
            val stagingProperties = Properties()
            stagingProperties.load(FileInputStream(stagingPropertiesFile))

            android {
                buildConfigField(STRING, "BUILD_FLAVOUR", "stagingDebugAndroid")
                buildConfigField(STRING, "BASE_URL", stagingBaseUrl)
                buildConfigField(BOOLEAN, "IsDebug", "true")
                buildConfigField(STRING, "FIREBASE_KEY", stagingProperties["FIREBASE_KEY"] as String)
                buildConfigField(
                    STRING,
                    "FIREBASE_CLIENT_ID",
                    stagingProperties["ANDROID_FIREBASE_CLIENT_ID"] as String
                )
            }

            ios {
                buildConfigField(STRING, "BUILD_FLAVOUR", "stagingDebugIos")
                buildConfigField(STRING, "BASE_URL", stagingBaseUrl)
                buildConfigField(BOOLEAN, "IsDebug", "true")
                buildConfigField(STRING, "FIREBASE_KEY", stagingProperties["FIREBASE_KEY"] as String)
                buildConfigField(
                    STRING,
                    "FIREBASE_CLIENT_ID",
                    stagingProperties["ANDROID_FIREBASE_CLIENT_ID"] as String
                )
            }

            wasmJS {
                buildConfigField(STRING, "BUILD_FLAVOUR", "stagingDebugWasmJS")
                buildConfigField(STRING, "BASE_URL", stagingBaseUrl)
                buildConfigField(BOOLEAN, "IsDebug", "true")
                buildConfigField(STRING, "FIREBASE_KEY", stagingProperties["FIREBASE_KEY"] as String)
                buildConfigField(STRING, "FIREBASE_CLIENT_ID", stagingProperties["WEBAPP_FIREBASE_CLIENT_ID"] as String)
            }
        }
    }
}

fun getCurrentVariant(): String {
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

fun setupStagingFirebase() {
    val firebaseConfig = """
                export const firebaseConfig = {
                    apiKey: "AIzaSyBuIB07aopv1LEdRi0QNvxsQppIMSIyw0M",
                    authDomain: "sportplatform-dev.firebaseapp.com",
                    projectId: "sportplatform-dev",
                    storageBucket: "sportplatform-dev.firebasestorage.app",
                    messagingSenderId: "716683021770",
                    appId: "1:716683021770:web:13fdee031e3ec36fec9333",
                    measurementId: "G-4J799NGSXJ"
                };
                    
            """.trimIndent()

    val moduleComposeAppDir = project(":composeApp").projectDir

    file("$moduleComposeAppDir/src/wasmJsMain/resources/firebase/firebaseConfig.js").writeText(firebaseConfig)
}


fun setupProductionFirebase() {
    val firebaseConfig = """
                export const firebaseConfig = {
                    apiKey: "AIzaSyC6NQgwckIiz7L5S7EVLHidsO8IByB3y_E",
                    authDomain: "sportplatform-b5318.firebaseapp.com",
                    projectId: "sportplatform-b5318",
                    storageBucket: "sportplatform-b5318.firebasestorage.app",
                    messagingSenderId: "607279059338",
                    appId: "1:607279059338:web:23cf771c199457fdcb3873",
                    measurementId: "G-N93CDPCXM5"
                };
            """.trimIndent()

    val moduleComposeAppDir = project(":composeApp").projectDir

    file("$moduleComposeAppDir/src/wasmJsMain/resources/firebase/firebaseConfig.js").writeText(firebaseConfig)
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
    val androidKMPFlavor = flavor.plus(variant.capitalized())

    val buildKonfigFlavor = when {
        gradle.startParameter.taskNames.contains("composeApp:wasmJsBrowserDevelopmentRun") -> {
            setupStagingFirebase()
            "stagingDebug"
        }
        gradle.startParameter.taskNames.contains("composeApp:wasmJsBrowserDistribution") -> {
            setupProductionFirebase()
            "productionRelease"
        }
        gradle.startParameter.taskNames.contains("composeApp:wasmJsBrowserProductionRun") -> {
            setupProductionFirebase()
            "productionRelease"
        }
        androidKMPFlavor.isEmpty() -> project.findProperty(KotlinCocoapodsPlugin.CONFIGURATION_PROPERTY).toString()
        else -> androidKMPFlavor
    }
    project.setProperty("buildkonfig.flavor", buildKonfigFlavor)
}

tasks.preBuild.dependsOn("setupBuildkonfig")

android {
    namespace = "pl.msiwak.multiplatform.buildconfig"
}

fun NamedDomainObjectContainer<TargetConfigDsl>.ios(block: TargetConfigDsl.() -> Unit) {
    listOf(
        "iosArm64",
        "iosSimulatorArm64",
        "iosX64"
    ).forEach {
        create(it, block)
    }
}

fun NamedDomainObjectContainer<TargetConfigDsl>.android(block: TargetConfigDsl.() -> Unit) {
    create("android", block)
}

fun NamedDomainObjectContainer<TargetConfigDsl>.wasmJS(block: TargetConfigDsl.() -> Unit) {
    create("wasmJs", block)
}
