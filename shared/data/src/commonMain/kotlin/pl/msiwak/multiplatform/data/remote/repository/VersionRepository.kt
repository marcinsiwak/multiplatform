package pl.msiwak.multiplatform.data.remote.repository

expect class VersionRepository(context: KMMVersionContext){
    fun getVersionName(): String
    fun getLongerVersionCode(): String
}