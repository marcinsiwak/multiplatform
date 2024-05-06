package pl.msiwak.multiplatform.ui.welcome

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.InputView
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.SecondaryButton
import pl.msiwak.multiplatform.ui.commonComponent.rememberGoogleLoginLauncherForActivityResult

@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeScreenViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    val startSignIn = rememberGoogleLoginLauncherForActivityResult(
        onResultOk = { idToken ->
            viewModel.onGoogleLogin(idToken, null)
        }
    )

    WelcomeScreenContent(
        viewState = viewState,
        onConfirmDialogButtonClicked = viewModel::onConfirmDialogButtonClicked,
        onConfirmSynchronizationClicked = viewModel::onConfirmSynchronizationClicked,
        onDismissSynchronizationClicked = viewModel::onDismissSynchronizationClicked,
        onLoginChanged = viewModel::onLoginChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onVisibilityClicked = viewModel::onVisibilityClicked,
        onLoginClicked = viewModel::onLoginClicked,
        onOfflineModeClicked = viewModel::onOfflineModeClicked,
        onRegistrationClicked = viewModel::onRegistrationClicked,
        onGoogleLoginClicked = { startSignIn() }
    )
}

@Composable
fun WelcomeScreenContent(
    viewState: State<WelcomeState>,
    onConfirmDialogButtonClicked: () -> Unit = {},
    onConfirmSynchronizationClicked: () -> Unit = {},
    onDismissSynchronizationClicked: () -> Unit = {},
    onLoginChanged: (String) -> Unit = {},
    onPasswordChanged: (String) -> Unit = {},
    onVisibilityClicked: () -> Unit = {},
    onLoginClicked: () -> Unit = {},
    onOfflineModeClicked: () -> Unit = {},
    onRegistrationClicked: () -> Unit = {},
    onGoogleLoginClicked: () -> Unit = {}

) {
    if (viewState.value.isErrorDialogVisible) {
        PopupDialog(
            title = stringResource(SR.strings.auth_failed_title),
            description = stringResource(SR.strings.auth_failed_description),
            confirmButtonTitle = stringResource(SR.strings.confirm),
            onConfirmClicked = {
                onConfirmDialogButtonClicked()
            }
        )
    }

    if (viewState.value.isSynchronizationDialogVisible) {
        PopupDialog(
            title = stringResource(SR.strings.synchronization_dialog_title),
            description = stringResource(SR.strings.synchronization_dialog_description),
            confirmButtonTitle = stringResource(SR.strings.confirm),
            dismissButtonTitle = stringResource(SR.strings.deny),
            onConfirmClicked = {
                onConfirmSynchronizationClicked()
            },
            onDismissClicked = {
                onDismissSynchronizationClicked()
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            painter = painterResource(id = SR.images.bg_welcome_screen.drawableResId),
//            contentScale = ContentScale.Crop,
//            contentDescription = "Welcome screen"
//        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.Center)
                .width(IntrinsicSize.Min)
                .padding(
                    start = MaterialTheme.dimens.space_36,
                    end = MaterialTheme.dimens.space_36,
                    top = MaterialTheme.dimens.space_164
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            InputView(
                modifier = Modifier.padding(top = MaterialTheme.dimens.space_64),
                value = viewState.value.login,
                errorMessage = viewState.value.authErrorMessage,
                onValueChange = {
                    onLoginChanged(it)
                },
                hintText = stringResource(SR.strings.email)
            )

            InputView(
                modifier = Modifier,
                value = viewState.value.password,
                errorMessage = viewState.value.authErrorMessage,
                onValueChange = {
                    onPasswordChanged(it)
                },
                isPasswordVisible = viewState.value.isPasswordVisible,
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable { onVisibilityClicked() },
                        painter = painterResource(
                            imageResource = if (viewState.value.isPasswordVisible) {
                                SR.images.ic_invisible
                            } else {
                                SR.images.ic_visible
                            }
                        ),
                        contentDescription = null
                    )
                },
                isPassword = true,
                hintText = stringResource(SR.strings.password)
            )

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.dimens.space_24),
                onClick = { onLoginClicked() },
                text = stringResource(SR.strings.login)
            )

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.dimens.space_16
                    ),
                onClick = {
                    onGoogleLoginClicked()
                },
                text = stringResource(SR.strings.welcome_google_login)
            )

            SecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.dimens.space_16,
                        bottom = MaterialTheme.dimens.space_16
                    ),
                onClick = { onOfflineModeClicked() },
                text = stringResource(SR.strings.welcome_offline_mode)
            )

            Text(
                text = stringResource(SR.strings.welcome_no_account),
                color = MaterialTheme.colorScheme.secondary
            )
            Button(
                onClick = {
                    onRegistrationClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(text = stringResource(SR.strings.welcome_create_account))
            }
        }
    }
}

// @DarkLightPreview()
// @Composable
// fun WelcomeScreenPreview() {
//     AppTheme {
//         WelcomeScreenContent(MutableStateFlow(WelcomeState()).collectAsState())
//     }
// }
