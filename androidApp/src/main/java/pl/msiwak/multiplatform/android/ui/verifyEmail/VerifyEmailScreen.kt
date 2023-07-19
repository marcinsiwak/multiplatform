package pl.msiwak.multiplatform.android.ui.verifyEmail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
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
            when(it) {
                VerifyEmailEvent.OpenMail -> context.openMailApp()
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(MaterialTheme.dimens.space_8),
            text = "Verify your account",
            fontSize = MaterialTheme.font.font_24
        )
        Text(
            modifier = Modifier.padding(MaterialTheme.dimens.space_8),
            text = "Check your email",
            fontSize = MaterialTheme.font.font_16
        )

        MainButton(
            modifier = Modifier,
            onClick = { viewModel.onOpenMailClicked() },
            text = "Open email"
        )

        SecondaryButton(
            modifier = Modifier,
            onClick = { viewModel.onResendMailClicked() },
            text = "Resend email"
        )

        MainButton(
            modifier = Modifier,
            onClick = { viewModel.onLoginClicked() },
            text = "Login"
        )
    }
}
