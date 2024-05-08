package pl.msiwak.multiplatform.commonResources.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
private fun appColorScheme() = lightColorScheme(
    primary = White,
    onPrimary = Black,
    secondary = DarkGrey,
    onSecondary = Black,
    tertiary = LightGrey,
    onTertiary = Black,
    surface = Black,
    onSurface = White,
    background = Black,
    error = Red,
    onError = Red
)

@Composable
private fun appColorSchemeDark() = darkColorScheme(
    primary = Black,
    onPrimary = White,
    secondary = DarkGrey,
    onSecondary = Black,
    tertiary = LightGrey,
    onTertiary = Black,
    surface = Black,
    onSurface = White,
    background = Black,
    error = Red,
    onError = Red
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = if (true) {
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
