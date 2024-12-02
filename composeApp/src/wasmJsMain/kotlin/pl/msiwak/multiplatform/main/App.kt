package pl.msiwak.multiplatform.main

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.koin.core.context.startKoin
import pl.msiwak.multiplatform.shared.MainView
import pl.msiwak.multiplatform.shared.appModule
import pl.msiwak.multiplatform.shared.databaseModule
import pl.msiwak.multiplatform.shared.repositoryUseModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule() + databaseModule + repositoryUseModule)
    }
    try {
        print("Starting WASM application...")
        // Your initialization code here

        ComposeViewport(document.body!!) {
            MainView()
        }
    } catch (e: Exception) {
        println("Error occurred in WASM application: $e")
    }
}
