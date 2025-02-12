package pl.msiwak.multiplatform.shared

import androidx.compose.ui.window.ComposeUIViewController
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.shared.main.MainView

fun setup() = ComposeUIViewController() {
    AppTheme {
        MainView()
    }
}
