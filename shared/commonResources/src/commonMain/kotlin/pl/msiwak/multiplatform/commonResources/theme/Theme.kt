package pl.msiwak.multiplatform.commonResources.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import dev.icerock.moko.resources.compose.colorResource
import pl.msiwak.multiplatform.commonResources.SR

@Composable
private fun appColorScheme() = lightColorScheme(
    primary = colorResource(SR.colors.colorPrimary),
    onPrimary = colorResource(SR.colors.colorOnPrimary),
    secondary = colorResource(SR.colors.colorSecondary),
    onSecondary = colorResource(SR.colors.colorOnSecondary),
    tertiary = colorResource(SR.colors.colorTertiary),
    onTertiary = colorResource(SR.colors.colorOnTertiary),
    surface = colorResource(SR.colors.colorSurface),
    onSurface = colorResource(SR.colors.colorOnSurface),
    background = colorResource(SR.colors.background),
    error = colorResource(SR.colors.colorError),
    onError = colorResource(SR.colors.colorOnError)
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = appColorScheme()

    CompositionLocalProvider(LocalOwnColorScheme provides OwnColorScheme(true)) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
