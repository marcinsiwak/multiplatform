package pl.msiwak.multiplatform.data.remote.repository

import platform.Foundation.NSBundle

class VersionRepositoryImpl(private val nsBundle: NSBundle) : VersionRepository {

    override fun getVersionName(): String {
        return nsBundle.infoDictionary?.get("CFBundleShortVersionString") as String
    }

    override fun getLongerVersionCode(): String {
        return nsBundle.infoDictionary?.get("CFBundleVersion") as String
    }
}
