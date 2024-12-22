package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.remoteConfig.RemoteConfig

class RemoteConfigRepository(private val remoteConfig: RemoteConfig) {
    suspend fun fetch() = withContext(Dispatchers.Main) {
        remoteConfig.fetch()
    }

    fun getMinVersion(): String = remoteConfig.getMinVersion()
    fun getLastVersion(): String = remoteConfig.getLastVersion()
}
