package pl.msiwak.multiplatform.remoteConfig

expect class RemoteConfig() {
    suspend fun fetch()

    fun getMinVersion(): String
    fun getLastVersion(): String
}
