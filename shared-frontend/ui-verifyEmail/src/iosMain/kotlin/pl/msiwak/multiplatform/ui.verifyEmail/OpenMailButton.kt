package pl.msiwak.multiplatform.ui.verifyEmail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import athletetrack.shared_frontend.commonresources.generated.resources.Res
import athletetrack.shared_frontend.commonresources.generated.resources.verify_open_mail
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

@Composable
actual fun OpenMailButton(modifier: Modifier) {
    MainButton(
        modifier = modifier,
        onClick = {
            UIApplication.sharedApplication().openURL(NSURL(string = "message://"))
        },
        text = stringResource(Res.string.verify_open_mail)
    )
}
