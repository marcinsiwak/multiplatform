package pl.msiwak.multiplatform.core.repository

expect class VersionRepository(context: KMMVersionContext){
    fun getVersionName(): String
    fun getLongerVersionCode(): String
}