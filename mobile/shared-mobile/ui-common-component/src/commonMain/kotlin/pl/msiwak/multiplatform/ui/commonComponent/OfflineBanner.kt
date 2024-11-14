package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import athletetrack.shared_mobile.commonresources.generated.resources.Res
import athletetrack.shared_mobile.commonresources.generated.resources.ic_arrow_right
import athletetrack.shared_mobile.commonresources.generated.resources.offline_mode_sing_up_in
import athletetrack.shared_mobile.commonresources.generated.resources.offline_mode_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.commonResources.theme.dimens

@Composable
fun OfflineBanner(
    modifier: Modifier = Modifier,
    onSignInUpClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(MaterialTheme.dimens.space_8),
            text = stringResource(Res.string.offline_mode_title),
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onTertiary
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .clickable { onSignInUpClicked() }
                .padding(horizontal = MaterialTheme.dimens.space_8),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(Res.string.offline_mode_sing_up_in),
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiary
            )

            Icon(
                painter = painterResource(Res.drawable.ic_arrow_right),
                tint = MaterialTheme.colorScheme.onTertiary,
                contentDescription = null
            )
        }
    }
}
