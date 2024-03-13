package pl.msiwak.multiplatform.ui.verifyEmail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.SecondaryButton

@Composable
fun VerifyEmailScreen(
    viewModel: VerifyEmailViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

//    val context = LocalContext.current
//
//    LaunchedEffect(key1 = Unit) {
//        viewModel.viewEvent.collectLatest {
//            when (it) {
//                VerifyEmailEvent.OpenMail -> openMailApp()
//            }
//        }
//    }

    VerifyEmailScreenContent(
        viewState = viewState,
        onOpenMailClicked = viewModel::onOpenMailClicked,
        onResendMailClicked = viewModel::onResendMailClicked,
        onLoginClicked = viewModel::onLoginClicked
    )
}

@Composable
fun VerifyEmailScreenContent(
    viewState: State<VerifyState>,
    onOpenMailClicked: () -> Unit = {},
    onResendMailClicked: () -> Unit = {},
    onLoginClicked: () -> Unit = {}
) {
    if (viewState.value.isLoading) {
        Loader()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                vertical = MaterialTheme.dimens.space_32,
                horizontal = MaterialTheme.dimens.space_16
            )
    ) {
        Text(
            modifier = Modifier.padding(MaterialTheme.dimens.space_8),
            text = stringResource(SR.strings.verify_title),
            fontSize = MaterialTheme.font.font_24
        )
        Text(
            modifier = Modifier.padding(MaterialTheme.dimens.space_8),
            text = stringResource(SR.strings.verify_description),
            fontSize = MaterialTheme.font.font_16
        )

        Spacer(modifier = Modifier.weight(1f))

        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.dimens.space_8),
            onClick = { onOpenMailClicked() },
            text = stringResource(SR.strings.verify_open_mail)
        )

        SecondaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onResendMailClicked() },
            text = stringResource(SR.strings.verify_resend_mail)
        )

        MainButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.dimens.space_8),
            onClick = { onLoginClicked() },
            text = stringResource(SR.strings.verify_login)
        )
    }
}

// @DarkLightPreview
// @Composable
// fun VerifyEmailScreenPreview() {
//     AppTheme {
//         VerifyEmailScreenContent(MutableStateFlow(VerifyState()).collectAsState())
//     }
// }
