package pl.msiwak.multiplatform.core.api.remoteConfig

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.FirebaseRemoteConfig
import dev.gitlive.firebase.remoteconfig.remoteConfig

class RemoteConfig {
    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig

    suspend fun fetch() {
        remoteConfig.fetchAndActivate()
    }

    fun getMinVersion() = remoteConfig.getValue(FORCE_UPDATE_MIN_VERSION_KEY)
    fun getLastVersion() = remoteConfig.getValue(FORCE_UPDATE_LAST_VERSION_KEY)

    companion object {
        private const val FORCE_UPDATE_MIN_VERSION_KEY = "and_min_version"
        private const val FORCE_UPDATE_LAST_VERSION_KEY = "and_last_version"
    }
}