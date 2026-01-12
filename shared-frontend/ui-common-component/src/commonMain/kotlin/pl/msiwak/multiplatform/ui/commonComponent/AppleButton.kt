package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun AppleButton(modifier: Modifier, callback: (String?, String?, String?) -> Unit)
