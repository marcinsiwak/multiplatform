package pl.msiwak.multiplatform.main

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import androidx.navigation.compose.rememberNavController
import kotlinx.browser.document
import org.koin.core.context.startKoin
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.shared.MainView
import pl.msiwak.multiplatform.shared.appModule
import pl.msiwak.multiplatform.shared.databaseModule
import pl.msiwak.multiplatform.shared.platformRepositoryModule
import pl.msiwak.multiplatform.ui.language.LanguageScreen

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule() + platformRepositoryModule + databaseModule)

    }
    try {
        print("Starting WASM application...")
        // Your initialization code here

        ComposeViewport(document.body!!) {
                MainView()
        }
    } catch (e: Exception) {
        println("Error new occurred in WASM application: ${e.message}")
    }
}
