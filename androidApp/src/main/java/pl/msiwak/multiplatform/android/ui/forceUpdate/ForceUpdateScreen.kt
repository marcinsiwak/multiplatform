package pl.msiwak.multiplatform.android.ui.forceUpdate

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.java.KoinJavaComponent
import pl.msiwak.multiplatform.android.ui.components.MainButton
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.commonResources.MR

@Composable
fun ForceUpdateScreen() {
    val viewModel: pl.msiwak.multiplatform.ui.forceUpdate.ForceUpdateViewModel by KoinJavaComponent.inject(
        pl.msiwak.multiplatform.ui.forceUpdate.ForceUpdateViewModel::class.java)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Image(
                painter = painterResource(id = MR.images.bg_force_update.drawableResId),
                contentScale = ContentScale.FillWidth,
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(MaterialTheme.dimens.space_16),
                text = stringResource(MR.strings.force_update_title.resourceId),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = MaterialTheme.font.font_24
            )
            Text(
                modifier = Modifier.padding(horizontal = MaterialTheme.dimens.space_16),
                text = stringResource(MR.strings.force_update_description.resourceId),
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
            onClick = { viewModel.onUpdateClicked() },
            text = stringResource(id = MR.strings.force_update_update.resourceId)
        )
    }
}

@Preview
@Composable
fun ForceUpdateScreenPreview() {
    ForceUpdateScreen()
}
