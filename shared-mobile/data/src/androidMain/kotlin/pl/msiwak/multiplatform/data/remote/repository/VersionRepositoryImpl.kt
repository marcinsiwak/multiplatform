package pl.msiwak.multiplatform.data.remote.repository

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

class VersionRepositoryImpl(private val context: Context) : VersionRepository {

    override fun getVersionName(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.PackageInfoFlags.of(0)
            ).versionName
        } else {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        }
    }

    override fun getLongerVersionCode(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.PackageInfoFlags.of(0)
            ).longVersionCode.toString()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            context.packageManager.getPackageInfo(context.packageName, 0).longVersionCode.toString()
        } else {
            context.packageManager.getPackageInfo(context.packageName, 0).versionCode.toString()
        }
    }
}
