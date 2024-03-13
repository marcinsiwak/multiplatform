package pl.msiwak.multiplatform.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.ui.commonComponent.defaultDelegateConfiguration
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
fun setup(view: UIView) = ComposeUIViewController(
    configure = {
        delegate = defaultDelegateConfiguration
    }
) {
    AppTheme {
        MainView(
            modifier = Modifier,
            content = { UIKitView(modifier = Modifier.fillMaxSize(), factory = { view }) }
        )
    }
}
