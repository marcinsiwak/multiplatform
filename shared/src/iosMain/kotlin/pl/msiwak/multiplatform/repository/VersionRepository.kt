package pl.msiwak.multiplatform.repository

actual class VersionRepository actual constructor(private val context: KMMVersionContext) {
    actual fun getVersionName(): String {
        return context.infoDictionary?.get("CFBundleShortVersionString") as String
    }

    actual fun getLongerVersionCode(): String {
        return context.infoDictionary?.get("CFBundleVersion") as String
    }
}