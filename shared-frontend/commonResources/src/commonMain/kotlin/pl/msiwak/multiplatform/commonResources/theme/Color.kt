package pl.msiwak.multiplatform.commonResources.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Suppress("ConstructorParameterNaming")
data class OwnColorPalette(
    val ShadowColor: Color = Color(0x80444444),
    val HintColor: Color = Color.Gray,
    val AppleIcon: Color = Color.Black,
)

private val OwnDarkColorPalette = OwnColorPalette(
    ShadowColor = Color(0x80444444),
    AppleIcon = Color.White
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
