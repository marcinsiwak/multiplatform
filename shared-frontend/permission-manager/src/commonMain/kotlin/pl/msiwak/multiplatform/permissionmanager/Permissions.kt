package pl.msiwak.multiplatform.permissionmanager

expect interface PermissionListener {
    fun requestPermission(permission: AppPermission, callback: PermissionResultCallback?)
    fun isPermissionGranted(permission: AppPermission): Boolean
}

enum class AppPermission {
    NOTIFICATIONS
}

class PermissionBridge {

    private var listener: PermissionListener? = null

    fun setListener(listener: PermissionListener) {
        this.listener = listener
    }

    fun requestPermission(permission: AppPermission,callback: PermissionResultCallback? = null) {
        listener?.requestPermission(permission, callback) ?: error("Callback handler not set")
    }

    fun isPermissionGranted(permission: AppPermission): Boolean {
        return listener?.isPermissionGranted(permission) ?: false
    }
}

interface PermissionResultCallback {
    fun onPermissionGranted()
    fun onPermissionDenied(isPermanentDenied: Boolean)
}
