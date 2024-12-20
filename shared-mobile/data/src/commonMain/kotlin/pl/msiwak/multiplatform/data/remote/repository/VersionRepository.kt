package pl.msiwak.multiplatform.data.remote.repository

interface VersionRepository {
    fun getVersionName(): String
    fun getLongerVersionCode(): String
}
