package pl.msiwak.multiplatform.shared

import androidx.compose.ui.window.ComposeUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.ui.commonComponent.defaultDelegateConfiguration
import platform.UIKit.UIView

fun setup() = ComposeUIViewController(
    configure = {
        delegate = defaultDelegateConfiguration
    }
) {
    AppTheme {
        MainView()
    }
}
