package pl.msiwak.multiplatform.ui.terms

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun HtmlText(modifier: Modifier, text: String)
