package pl.msiwak.multiplatform.main

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.koin.compose.koinInject
import org.koin.core.context.startKoin
import org.koin.dsl.module
import pl.msiwak.multiplatform.permissionmanager.AppPermission
import pl.msiwak.multiplatform.permissionmanager.PermissionBridge
import pl.msiwak.multiplatform.permissionmanager.PermissionListener
import pl.msiwak.multiplatform.permissionmanager.PermissionResultCallback
import pl.msiwak.multiplatform.shared.appModule
import pl.msiwak.multiplatform.shared.main.MainView
import pl.msiwak.multiplatform.shared.modules.databaseModule
import pl.msiwak.multiplatform.shared.modules.platformRepositoryModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule() + platformRepositoryModule + databaseModule + permissionModule)
    }

    try {
        print("Starting WASM application...")
        ComposeViewport(document.body!!) {
            val permissionManager: PermissionListener = koinInject<PermissionListener>()
            val permissionBridge: PermissionBridge = koinInject<PermissionBridge>()

            permissionBridge.setListener(permissionManager)
            MainView()
        }
    } catch (e: Exception) {
        println("Error new occurred in WASM application: ${e.message}")
    }
}

val permissionModule = module {
    single<PermissionListener> { WebPermissionManager() }
}

class WebPermissionManager : PermissionListener {
    override fun requestPermission(permission: AppPermission, callback: PermissionResultCallback?) {
        // todo
    }

    override fun isPermissionGranted(permission: AppPermission): Boolean {
        // todo
        return false
    }
}