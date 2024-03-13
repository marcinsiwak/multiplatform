package pl.msiwak.multiplatform.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import pl.msiwak.multiplatform.commonResources.theme.OwnColorPalette

@Suppress("ConstructorParameterNaming")
data class OwnColorPalette(
    val ShadowColor: Color = Color.DarkGray,
    val HintColor: Color = Color.Gray
)

private val OwnDarkColorPalette = OwnColorPalette(
    ShadowColor = Color.Black
)

val LocalOwnColorScheme = compositionLocalOf { OwnColorPalette() }

val MaterialTheme.color: OwnColorPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalOwnColorScheme.current

@Composable
fun OwnColorScheme(isDarkTheme: Boolean) = if (isDarkTheme) {
    OwnDarkColorPalette
} else {
    OwnColorPalette()
}
