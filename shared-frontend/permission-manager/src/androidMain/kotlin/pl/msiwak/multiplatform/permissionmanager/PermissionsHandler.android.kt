package pl.msiwak.multiplatform.permissionmanager

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object PermissionsHandler {
    fun handlePermission(
        activity: Activity,
        permission: AppPermission,
        callback: PermissionResultCallback?,
        onMissingPermission: (String) -> Unit
    ) {
        val androidPermission = when (permission) {
            AppPermission.NOTIFICATIONS -> Manifest.permission.POST_NOTIFICATIONS
        }
        when {
            ContextCompat.checkSelfPermission(
                activity,
                androidPermission
            ) == PackageManager.PERMISSION_GRANTED -> {
                callback?.onPermissionGranted()
            }

            activity.shouldShowRequestPermissionRationale(androidPermission) -> {
                callback?.onPermissionDenied(false)
            }

            else -> onMissingPermission(androidPermission)
        }
    }

    fun checkIsPermissionGranted(activity: Activity, permission: AppPermission): Boolean {
        return ContextCompat.checkSelfPermission(
            activity,
            preparePermissionString(permission)
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun preparePermissionString(permission: AppPermission) = when (permission) {
        AppPermission.NOTIFICATIONS -> Manifest.permission.POST_NOTIFICATIONS
    }
}
