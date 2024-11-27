package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.runtime.Composable

@Composable
expect fun rememberGoogleLoginLauncherForActivityResult(onResultOk: (String, String?) -> Unit): () -> Unit
