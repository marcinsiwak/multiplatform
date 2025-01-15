package pl.msiwak.multiplatform.ui.verifyEmail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import athletetrack.shared_mobile.commonresources.generated.resources.Res
import athletetrack.shared_mobile.commonresources.generated.resources.verify_open_mail
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.extensions.openMail

@Composable
actual fun OpenMailButton(modifier: Modifier) {
    val context = LocalContext.current
    MainButton(
        modifier = modifier,
        onClick = { openMail(context) },
        text = stringResource(Res.string.verify_open_mail)
    )
}
