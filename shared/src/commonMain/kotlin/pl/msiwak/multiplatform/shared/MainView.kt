package pl.msiwak.multiplatform.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainView(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    content()
}
