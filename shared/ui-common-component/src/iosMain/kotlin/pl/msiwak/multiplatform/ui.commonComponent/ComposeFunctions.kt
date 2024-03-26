package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@Composable
actual fun rememberGoogleLoginLauncherForActivityResult(onResultOk: (String) -> Unit): () -> Unit {
    return {}
}

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun Icon(drawableResource: DrawableResource) {
    androidx.compose.material3.Icon(
        org.jetbrains.compose.resources.painterResource(drawableResource),
        null
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun stringResource(stringResource: StringResource): String {
    return org.jetbrains.compose.resources.stringResource(stringResource)
}
