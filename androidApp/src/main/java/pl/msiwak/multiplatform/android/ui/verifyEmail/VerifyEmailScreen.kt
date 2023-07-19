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
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.extensions.openMailApp
import pl.msiwak.multiplatform.android.ui.components.MainButton
import pl.msiwak.multiplatform.android.ui.components.SecondaryButton
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailEvent
import pl.msiwak.multiplatform.ui.verifyEmail.VerifyEmailViewModel

@Composable
fun VerifyEmailScreen() {
    val viewModel = koinViewModel<VerifyEmailViewModel>()

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        viewModel.viewEvent.collectLatest {
            when (it) {
                VerifyEmailEvent.OpenMail -> context.openMailApp()
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
            text = "Verify your account",
            fontSize = MaterialTheme.font.font_24
        )
        Text(
            modifier = Modifier.padding(MaterialTheme.dimens.space_8),
            text = "We have sent you activation link. Please check your email and verify your account.",
            fontSize = MaterialTheme.font.font_16
        )

        Spacer(modifier = Modifier.weight(1f))

        MainButton(
            modifier = Modifier.fillMaxWidth().padding(vertical = MaterialTheme.dimens.space_8),
            onClick = { viewModel.onOpenMailClicked() },
            text = "Open email"
        )

        SecondaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onResendMailClicked() },
            text = "Resend email"
        )

        MainButton(
            modifier = Modifier.fillMaxWidth().padding(vertical = MaterialTheme.dimens.space_8),
            onClick = { viewModel.onLoginClicked() },
            text = "Login"
        )
    }
}
