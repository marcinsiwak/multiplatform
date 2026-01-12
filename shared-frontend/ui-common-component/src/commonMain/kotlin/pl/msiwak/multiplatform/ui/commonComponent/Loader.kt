package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import pl.msiwak.multiplatform.commonResources.theme.Grey
import pl.msiwak.multiplatform.commonResources.theme.White
import pl.msiwak.multiplatform.commonResources.theme.color
import pl.msiwak.multiplatform.commonResources.theme.dimens

@Composable
fun Loader() {
    Popup(
        properties = PopupProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.color.ShadowColor),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(MaterialTheme.dimens.loader_size),
                color = MaterialTheme.colorScheme.onTertiary,
                trackColor = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
