package pl.msiwak.multiplatform.data.remote.repository

actual class VersionRepository actual constructor(context: KMMVersionContext) {
    actual fun getVersionName(): String {
        return "1.0.0"
    }

    actual fun getLongerVersionCode(): String {
        return "1.0.0"
    }
}