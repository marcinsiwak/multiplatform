package pl.msiwak.multiplatform.ui.forceUpdate

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import athletetrack.shared_frontend.commonresources.generated.resources.Res
import athletetrack.shared_frontend.commonresources.generated.resources.force_update_update
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.extensions.openStore

@Composable
actual fun GoToStoreButton(modifier: Modifier) {
    val context = LocalContext.current
    MainButton(
        modifier = modifier
            .fillMaxWidth(),
        onClick = {
            openStore(context)
        },
        text = stringResource(Res.string.force_update_update)
    )
}
