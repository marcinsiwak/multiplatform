package pl.msiwak.multiplatform.android.ui.verifyEmail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.extensions.openMailApp
import pl.msiwak.multiplatform.android.ui.components.MainButton
import pl.msiwak.multiplatform.android.ui.components.SecondaryButton
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.commonResources.MR

@Composable
fun VerifyEmailScreen() {
    val viewModel = koinViewModel<pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailViewModel>()

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.viewEvent.collectLatest {
            when (it) {
                pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailEvent.OpenMail -> context.openMailApp()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = MaterialTheme.dimens.space_32,
                horizontal = MaterialTheme.dimens.space_16
            ),
    ) {
        Text(
            modifier = Modifier.padding(MaterialTheme.dimens.space_8),
            text = stringResource(id = MR.strings.verify_title.resourceId),
            fontSize = MaterialTheme.font.font_24
        )
        Text(
            modifier = Modifier.padding(MaterialTheme.dimens.space_8),
            text = stringResource(id = MR.strings.verify_description.resourceId),
            fontSize = MaterialTheme.font.font_16
        )

        Spacer(modifier = Modifier.weight(1f))

        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.dimens.space_8),
            onClick = { viewModel.onOpenMailClicked() },
            text = stringResource(id = MR.strings.verify_open_mail.resourceId),
        )

        SecondaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onResendMailClicked() },
            text = stringResource(id = MR.strings.verify_resend_mail.resourceId),
        )

        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.dimens.space_8),
            onClick = { viewModel.onLoginClicked() },
            text = stringResource(id = MR.strings.verify_login.resourceId),
        )
    }
}
