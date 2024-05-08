package pl.msiwak.multiplatform.ui.verifyEmail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.verify_open_mail
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.MainButton

@Composable
actual fun OpenMailButton(modifier: Modifier) {
    MainButton(
        modifier = modifier,
        onClick = {
                  //todo handle open mail on ios
        },
        text = stringResource(Res.string.verify_open_mail)
    )
}
