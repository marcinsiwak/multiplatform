package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.runtime.Composable

@Composable
actual fun rememberGoogleLoginLauncherForActivityResult(onResultOk: (String, String?) -> Unit): () -> Unit {
    return {
        initializeGoogleLogin { idToken ->
            onResultOk(idToken, null)
        }
    }
}

external interface GoogleButtonOptions {
    var theme: String
    var size: String
}

external fun initializeGoogleLogin(callback: (String) -> Unit)
external fun printLog(): String
external fun renderGoogleButton(elementId: String, options: GoogleButtonOptions)
external fun promptGoogleLogin()
