package pl.msiwak.multiplatform.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val ColorPrimaryDark = Color.Black
val ColorOnPrimaryDark = Color.White
val ColorSecondaryDark = Color.DarkGray
val ColorOnSecondaryDark = Color.White
val ColorTertiaryDark = Color.Gray
val ColorOnTertiaryDark = Color.White
val ColorSurfaceDark = Color.Black
val ColorOnSurfaceDark = Color.DarkGray
val ColorBackgroundDark = Color.Black

val ColorPrimary = Color.White
val ColorOnPrimary = Color.Black
val ColorSecondary = Color.LightGray
val ColorOnSecondary = Color.Black
val ColorTertiary = Color.Gray
val ColorOnTertiary = Color.Black
val ColorSurface = Color.White
val ColorOnSurface = Color.Black
val ColorBackground = Color.White

data class OwnColorPalette(
    val ShadowColor: Color = Color.DarkGray,
    val ExerciseColor: Color = Color.LightGray,
)

private val OwnDarkColorPalette = OwnColorPalette(
    ShadowColor = Color.Black,
    ExerciseColor = Color.LightGray,
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
