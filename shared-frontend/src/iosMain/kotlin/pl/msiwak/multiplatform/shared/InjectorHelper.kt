package pl.msiwak.multiplatform.shared

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pl.msiwak.multiplatform.permissionmanager.PermissionBridge
import pl.msiwak.multiplatform.permissionmanager.PermissionListener

class PermissionsHelper() : KoinComponent {
    private val permissionBridge: PermissionBridge by inject()

    fun setListener(listener: PermissionListener) = permissionBridge.setListener(listener)
}