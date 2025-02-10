package pl.msiwak.multiplatform.main

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.koin.core.context.startKoin
import pl.msiwak.multiplatform.shared.appModule
import pl.msiwak.multiplatform.shared.main.MainView
import pl.msiwak.multiplatform.shared.modules.databaseModule
import pl.msiwak.multiplatform.shared.modules.platformRepositoryModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule() + platformRepositoryModule + databaseModule)
    }

    try {
        print("Starting WASM application...")
        ComposeViewport(document.body!!) {
            MainView()
        }
    } catch (e: Exception) {
        println("Error new occurred in WASM application: ${e.message}")
    }
}
