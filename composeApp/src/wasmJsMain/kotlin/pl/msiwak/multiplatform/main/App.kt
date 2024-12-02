package pl.msiwak.multiplatform.main

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.koin.core.context.startKoin
import pl.msiwak.multiplatform.shared.MainView
import pl.msiwak.multiplatform.shared.appModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule())
    }

    ComposeViewport(document.body!!) {
        MainView()
    }
}
