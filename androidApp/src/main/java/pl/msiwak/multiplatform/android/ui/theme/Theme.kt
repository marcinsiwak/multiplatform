package pl.msiwak.multiplatform.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.res.colorResource
import pl.msiwak.multiplatform.commonResources.MR

@Composable
private fun appColorScheme() = lightColorScheme(
    primary = colorResource(id = MR.colors.colorPrimary.resourceId),
    onPrimary = colorResource(id = MR.colors.colorOnPrimary.resourceId),
    secondary = colorResource(id = MR.colors.colorSecondary.resourceId),
    onSecondary = colorResource(id = MR.colors.colorOnSecondary.resourceId),
    tertiary = colorResource(id = MR.colors.colorTertiary.resourceId),
    onTertiary = colorResource(id = MR.colors.colorOnTertiary.resourceId),
    surface = colorResource(id = MR.colors.colorSurface.resourceId),
    onSurface = colorResource(id = MR.colors.colorOnSurface.resourceId),
    background = colorResource(id = MR.colors.background.resourceId),
    error = colorResource(id = MR.colors.colorError.resourceId),
    onError = colorResource(id = MR.colors.colorOnError.resourceId)
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
