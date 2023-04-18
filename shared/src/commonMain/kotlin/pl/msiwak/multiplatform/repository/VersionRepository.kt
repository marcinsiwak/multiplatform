package pl.msiwak.multiplatform.repository

expect class VersionRepository(context: KMMVersionContext){
    fun getVersionName(): String
    fun getLongerVersionCode(): String
}