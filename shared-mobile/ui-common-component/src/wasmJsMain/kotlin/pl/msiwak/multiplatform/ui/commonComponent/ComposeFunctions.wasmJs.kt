package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.runtime.Composable

@Composable
actual fun rememberGoogleLoginLauncherForActivityResult(onResultOk: (String, String?) -> Unit): () -> Unit {
    return {}
}