package pl.msiwak.multiplatform.android.ui.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.commonResources.MR

@Composable
fun OfflineBanner(
    modifier: Modifier = Modifier,
    onSignInUpClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(MaterialTheme.dimens.space_8),
            text = stringResource(id = MR.strings.offline_mode_title.resourceId),
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
                text = stringResource(id = MR.strings.offline_mode_sing_up_in.resourceId),
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onTertiary
            )

            Icon(
                painter = painterResource(id = MR.images.ic_arrow_right.drawableResId),
                tint = MaterialTheme.colorScheme.onTertiary,
                contentDescription = null
            )
        }
    }
}