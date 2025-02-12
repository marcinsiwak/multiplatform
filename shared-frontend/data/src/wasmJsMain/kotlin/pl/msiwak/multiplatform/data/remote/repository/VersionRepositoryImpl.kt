package pl.msiwak.multiplatform.data.remote.repository

class VersionRepositoryImpl : VersionRepository {

    override fun getVersionName(): String {
        return "Athlete track (1.0.0)"
    }

    override fun getLongerVersionCode(): String {
        return "1000000"
    }
}
