package pl.msiwak.multiplatform.permissionmanager

actual interface PermissionListener {
    actual fun requestPermission(permission: AppPermission, callback: PermissionResultCallback)
    actual fun isPermissionGranted(permission: AppPermission): Boolean
}