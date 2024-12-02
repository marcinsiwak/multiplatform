package pl.msiwak.multiplatform.data.remote.repository

import kotlinx.coroutines.withContext
import pl.msiwak.multiplatform.remoteConfig.RemoteConfig

class RemoteConfigRepository(private val remoteConfig: RemoteConfig) {
    suspend fun fetch() = withContext(Dispatchers.IO) {
        remoteConfig.fetch()
    }

    fun getMinVersion() = remoteConfig.getMinVersion()
    fun getLastVersion() = remoteConfig.getLastVersion()
}
