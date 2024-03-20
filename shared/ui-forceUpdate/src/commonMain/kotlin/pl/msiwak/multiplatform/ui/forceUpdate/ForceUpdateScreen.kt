@file:OptIn(ExperimentalResourceApi::class)

package pl.msiwak.multiplatform.ui.forceUpdate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.bg_force_update
import athletetrack.shared.commonresources.generated.resources.force_update_description
import athletetrack.shared.commonresources.generated.resources.force_update_title
import athletetrack.shared.commonresources.generated.resources.force_update_update
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.ui.commonComponent.MainButton

@Composable
fun ForceUpdateScreen(
    viewModel: ForceUpdateViewModel = koinInject()
) {
    ForceUpdateScreenContent(
        onUpdateClicked = viewModel::onUpdateClicked
    )
}

@Composable
fun ForceUpdateScreenContent(
    onUpdateClicked: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Image(
                painter = painterResource(Res.drawable.bg_force_update),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(MaterialTheme.dimens.space_16),
                text = stringResource(Res.string.force_update_title),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = MaterialTheme.font.font_24
            )
            Text(
                modifier = Modifier.padding(horizontal = MaterialTheme.dimens.space_16),
                text = stringResource(Res.string.force_update_description),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = MaterialTheme.font.font_16
            )
        }
        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(
                    vertical = MaterialTheme.dimens.space_16,
                    horizontal = MaterialTheme.dimens.space_80
                ),
            onClick = onUpdateClicked,
            text = stringResource(Res.string.force_update_update)
        )
    }
}

// @DarkLightPreview
// @Composable
// fun ForceUpdateScreenPreview() {
//     AppTheme {
//         ForceUpdateScreenContent()
//     }
// }
