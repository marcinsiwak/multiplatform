package pl.msiwak.multiplatform.commonResources.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
private fun appColorScheme() = lightColorScheme(
    primary = White,
    onPrimary = Black,
    secondary = Grey,
    onSecondary = Black,
    tertiary = LightGrey,
    onTertiary = Black,
    surface = SurfaceGray,
    onSurface = Black,
    background = White,
    onBackground = Black,
    error = Red,
    onError = White
)

@Composable
private fun appColorSchemeDark() = darkColorScheme(
    primary = Black,
    onPrimary = White,
    secondary = DarkGrey,
    onSecondary = Black,
    tertiary = Black,
    onTertiary = White,
    surface = Black,
    onSurface = White,
    background = Black,
    error = Red,
    onError = Red
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        appColorSchemeDark()
    } else {
        appColorScheme()
    }

    CompositionLocalProvider(LocalOwnColorScheme provides OwnColorScheme(true)) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
