package pl.msiwak.multiplatform.ui.verifyEmail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.verify_description
import athletetrack.shared.commonresources.generated.resources.verify_login
import athletetrack.shared.commonresources.generated.resources.verify_resend_mail
import athletetrack.shared.commonresources.generated.resources.verify_title
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.SecondaryButton
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

@Composable
fun VerifyEmailScreen(
    navController: NavController,
    viewModel: VerifyEmailViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    VerifyEmailScreenContent(
        navController = navController,
        viewState = viewState,
        onResendMailClicked = viewModel::onResendMailClicked,
        onLoginClicked = {
            navController.navigate(NavDestination.WelcomeDestination.NavWelcomeScreen.route)
        }
    )
}

@Composable
fun VerifyEmailScreenContent(
    navController: NavController,
    viewState: State<VerifyState>,
    onResendMailClicked: () -> Unit = {},
    onLoginClicked: () -> Unit = {}
) {
    if (viewState.value.isLoading) {
        Loader()
    }

    Scaffold(
        topBar = {
            AppBar(navController = navController, title = stringResource(Res.string.verify_title))
        },
        content = {
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
                    text = stringResource(Res.string.verify_description),
                    fontSize = MaterialTheme.font.font_16
                )

                Spacer(modifier = Modifier.weight(1f))

                OpenMailButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.dimens.space_8)
                )

                SecondaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onResendMailClicked() },
                    text = stringResource(Res.string.verify_resend_mail)
                )

                MainButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.dimens.space_8),
                    onClick = { onLoginClicked() },
                    text = stringResource(Res.string.verify_login)
                )
            }
        }
    )
}

@DarkLightPreview
@Composable
fun VerifyEmailScreenPreview() {
    AppTheme {
        VerifyEmailScreenContent(
            rememberNavController(),
            MutableStateFlow(VerifyState()).collectAsState()
        )
    }
}
