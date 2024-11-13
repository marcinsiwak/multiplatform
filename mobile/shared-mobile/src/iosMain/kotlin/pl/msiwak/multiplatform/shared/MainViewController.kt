package pl.msiwak.multiplatform.shared

import androidx.compose.ui.window.ComposeUIViewController
import pl.msiwak.multiplatform.commonResources.theme.AppTheme

fun setup() = ComposeUIViewController() {
    AppTheme {
        MainView()
    }
}
