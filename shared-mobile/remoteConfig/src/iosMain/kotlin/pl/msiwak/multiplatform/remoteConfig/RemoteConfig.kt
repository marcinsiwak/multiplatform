package pl.msiwak.multiplatform.remoteConfig

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.FirebaseRemoteConfig
import dev.gitlive.firebase.remoteconfig.remoteConfig

actual class RemoteConfig {
    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

    actual suspend fun fetch() {
        remoteConfig.fetchAndActivate()
    }

    actual fun getMinVersion() = remoteConfig.getValue(FORCE_UPDATE_MIN_VERSION_KEY).asString()
    actual fun getLastVersion() = remoteConfig.getValue(FORCE_UPDATE_LAST_VERSION_KEY).asString()

    companion object {
        private const val FORCE_UPDATE_MIN_VERSION_KEY = "and_min_version"
        private const val FORCE_UPDATE_LAST_VERSION_KEY = "and_last_version"
    }
}
