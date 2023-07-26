package pl.msiwak.multiplatform.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = ColorPrimary,
    onPrimary = ColorOnPrimary,
    secondary = ColorSecondary,
    onSecondary = ColorOnSecondary,
    tertiary = ColorTertiary,
    onTertiary = ColorOnTertiary,
    surface = ColorSurface,
    onSurface = ColorOnSurface,
    background = ColorBackground,
    error = Color.Red,
)

private val DarkColorScheme = darkColorScheme(
    primary = ColorPrimaryDark,
    onPrimary = ColorOnPrimaryDark,
    secondary = ColorSecondaryDark,
    onSecondary = ColorOnSecondaryDark,
    tertiary = ColorTertiaryDark,
    onTertiary = ColorOnTertiaryDark,
    surface = ColorSurfaceDark,
    onSurface = ColorOnSurfaceDark,
    background = ColorBackgroundDark,
    error = Color.Red

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun BaseKmm_ProjectTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isDarkTheme -> DarkColorScheme
        else -> {
//            LightColorScheme light mode looks terrible
            DarkColorScheme
        }
    }

    CompositionLocalProvider(LocalOwnColorScheme provides OwnColorScheme(true)) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }

}