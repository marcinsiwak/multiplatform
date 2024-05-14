package pl.msiwak.multiplatform.shared

import androidx.compose.ui.window.ComposeUIViewController
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.ui.commonComponent.defaultDelegateConfiguration

fun setup() = ComposeUIViewController(
    configure = {
        delegate = defaultDelegateConfiguration
    }
) {
    AppTheme {
        MainView()
    }
}
