package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.runtime.Composable
import pl.msiwak.multiplatform.buildconfig.BuildConfig

@Composable
actual fun rememberGoogleLoginLauncherForActivityResult(onResultOk: (String, String?) -> Unit): () -> Unit {
    return {
        initializeGoogleLogin(clientId = BuildConfig.FIREBASE_CLIENT_ID) { idToken ->
            onResultOk(idToken, null)
        }
    }
}

external fun initializeGoogleLogin(clientId: String, callback: (String) -> Unit)
