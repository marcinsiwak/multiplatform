package pl.msiwak.multiplatform.ui.terms

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.WebKit.WKWebView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun HtmlText(modifier: Modifier, text: String) {
    UIKitView(
        factory = {
            val webView = WKWebView()
            webView.loadHTMLString(string = text, baseURL = null)
            webView
        },
        modifier = Modifier.fillMaxWidth()
    )
}
