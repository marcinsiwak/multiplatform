package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@Composable
expect fun rememberGoogleLoginLauncherForActivityResult(onResultOk: (String) -> Unit): () -> Unit

@OptIn(ExperimentalResourceApi::class)
@Composable
expect fun Icon(drawableResource: DrawableResource)

@OptIn(ExperimentalResourceApi::class)
@Composable
expect fun stringResource(stringResource: StringResource): String