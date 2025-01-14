package pl.msiwak.multiplatform.main

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.koin.core.context.startKoin
import pl.msiwak.multiplatform.shared.MainView
import pl.msiwak.multiplatform.shared.appModule
import pl.msiwak.multiplatform.shared.databaseModule
import pl.msiwak.multiplatform.shared.platformRepositoryModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule() + platformRepositoryModule + databaseModule)
    }

    ComposeViewport(document.body!!) {
        MainView()
    }
}
