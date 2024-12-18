package pl.msiwak.multiplatform.ui.terms

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichText

@Composable
actual fun HtmlText(modifier: Modifier, text: String) {
    val state = rememberRichTextState()
    state.setHtml(text)

    RichText(
        modifier = Modifier.fillMaxSize(),
        state = state
    )
}
