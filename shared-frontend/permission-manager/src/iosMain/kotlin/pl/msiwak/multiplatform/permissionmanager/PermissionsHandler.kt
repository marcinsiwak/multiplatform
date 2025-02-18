package pl.msiwak.multiplatform.permissionmanager

import kotlinx.coroutines.runBlocking
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationStatusAuthorized
import platform.UserNotifications.UNUserNotificationCenter
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object PermissionsHandler {
    fun handlePermission(
        permission: AppPermission,
        callback: PermissionResultCallback?
    ) {
        when (permission) {
            AppPermission.NOTIFICATIONS -> {
                UNUserNotificationCenter.currentNotificationCenter().requestAuthorizationWithOptions(
                    options = UNAuthorizationOptionAlert or UNAuthorizationOptionSound or UNAuthorizationOptionBadge
                ) { granted, _ ->
                    if (granted) {
                        callback?.onPermissionGranted()
                    } else {
                        callback?.onPermissionDenied(false)
                    }
                }
            }
        }
    }

    fun checkIsPermissionGranted(permission: AppPermission): Boolean {
        return when (permission) {
            AppPermission.NOTIFICATIONS -> checkNotificationsPermission()
        }
    }

    private fun checkNotificationsPermission(): Boolean = runBlocking {
        suspendCoroutine {
            UNUserNotificationCenter.currentNotificationCenter()
                .getNotificationSettingsWithCompletionHandler { settings ->
                    it.resume(settings?.authorizationStatus == UNAuthorizationStatusAuthorized)
                }
        }
    }
}
