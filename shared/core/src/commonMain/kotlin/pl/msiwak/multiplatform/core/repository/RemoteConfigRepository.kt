package pl.msiwak.multiplatform.core.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.core.api.remoteConfig.RemoteConfig

class RemoteConfigRepository(private val remoteConfig: RemoteConfig) {
    suspend fun fetch() = withContext(Dispatchers.Main) {
        remoteConfig.fetch()
    }

    fun getMinVersion() = remoteConfig.getMinVersion()
    fun getLastVersion() = remoteConfig.getLastVersion()
}