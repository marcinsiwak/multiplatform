package pl.msiwak.multiplatform.remoteConfig

actual class RemoteConfig {

    actual suspend fun fetch() {}

    actual fun getMinVersion(): String {
        return "1000000"
    }

    actual fun getLastVersion(): String {
        return "1000000"
    }
}
