package pl.msiwak.multiplatform.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.res.colorResource
import pl.msiwak.multiplatform.commonResources.SR

@Composable
private fun appColorScheme() = lightColorScheme(
    primary = colorResource(id = SR.colors.colorPrimary.resourceId),
    onPrimary = colorResource(id = SR.colors.colorOnPrimary.resourceId),
    secondary = colorResource(id = SR.colors.colorSecondary.resourceId),
    onSecondary = colorResource(id = SR.colors.colorOnSecondary.resourceId),
    tertiary = colorResource(id = SR.colors.colorTertiary.resourceId),
    onTertiary = colorResource(id = SR.colors.colorOnTertiary.resourceId),
    surface = colorResource(id = SR.colors.colorSurface.resourceId),
    onSurface = colorResource(id = SR.colors.colorOnSurface.resourceId),
    background = colorResource(id = SR.colors.background.resourceId),
    error = colorResource(id = SR.colors.colorError.resourceId),
    onError = colorResource(id = SR.colors.colorOnError.resourceId)
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
