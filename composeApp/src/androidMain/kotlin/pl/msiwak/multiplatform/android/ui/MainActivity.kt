package pl.msiwak.multiplatform.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.permissionmanager.AppPermission
import pl.msiwak.multiplatform.permissionmanager.PermissionBridge
import pl.msiwak.multiplatform.permissionmanager.PermissionListener
import pl.msiwak.multiplatform.permissionmanager.PermissionResultCallback
import pl.msiwak.multiplatform.permissionmanager.PermissionsHandler
import pl.msiwak.multiplatform.shared.MainView
import pl.msiwak.multiplatform.shared.MainViewModel

@Suppress("LongMethod")
class MainActivity : ComponentActivity(), PermissionListener {

    private val viewModel: MainViewModel by inject(MainViewModel::class.java)

    private val permissionBridge: PermissionBridge by inject(PermissionBridge::class.java)
    private var permissionResultCallback: PermissionResultCallback? = null

    private fun requestPermissionLauncher(permission: String): ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                permissionResultCallback?.onPermissionGranted()
            } else {
                val permanentlyDenied =
                    !shouldShowRequestPermissionRationale(permission)
                permissionResultCallback?.onPermissionDenied(permanentlyDenied)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        permissionBridge.setListener(this)

        setContent {
            val viewState = viewModel.viewState.collectAsState()
            installSplashScreen()
                .setKeepOnScreenCondition {
                    viewState.value.isLoading
                }
            MainView()
        }
    }

    override fun requestPermission(permission: AppPermission, callback: PermissionResultCallback) {
        PermissionsHandler.handlePermission(this, permission, callback) { androidPermission ->
            permissionResultCallback = callback
            requestPermissionLauncher(androidPermission).launch(androidPermission)
        }
    }

    override fun isPermissionGranted(permission: AppPermission) =
        PermissionsHandler.checkIsPermissionGranted(this, permission)
}
