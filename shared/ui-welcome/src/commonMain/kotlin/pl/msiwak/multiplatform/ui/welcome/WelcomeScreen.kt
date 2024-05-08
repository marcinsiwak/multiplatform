@file:OptIn(ExperimentalResourceApi::class)

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
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.auth_failed_description
import athletetrack.shared.commonresources.generated.resources.auth_failed_title
import athletetrack.shared.commonresources.generated.resources.confirm
import athletetrack.shared.commonresources.generated.resources.deny
import athletetrack.shared.commonresources.generated.resources.email
import athletetrack.shared.commonresources.generated.resources.ic_invisible
import athletetrack.shared.commonresources.generated.resources.ic_visible
import athletetrack.shared.commonresources.generated.resources.login
import athletetrack.shared.commonresources.generated.resources.password
import athletetrack.shared.commonresources.generated.resources.synchronization_dialog_description
import athletetrack.shared.commonresources.generated.resources.synchronization_dialog_title
import athletetrack.shared.commonresources.generated.resources.welcome_create_account
import athletetrack.shared.commonresources.generated.resources.welcome_google_login
import athletetrack.shared.commonresources.generated.resources.welcome_no_account
import athletetrack.shared.commonresources.generated.resources.welcome_offline_mode
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import androidx.navigation.NavController
import org.koin.compose.koinInject
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
            title = stringResource(Res.string.auth_failed_title),
            description = stringResource(Res.string.auth_failed_description),
            confirmButtonTitle = stringResource(Res.string.confirm),
            onConfirmClicked = {
                onConfirmDialogButtonClicked()
            }
        )
    }

    if (viewState.value.isSynchronizationDialogVisible) {
        PopupDialog(
            title = stringResource(Res.string.synchronization_dialog_title),
            description = stringResource(Res.string.synchronization_dialog_description),
            confirmButtonTitle = stringResource(Res.string.confirm),
            dismissButtonTitle = stringResource(Res.string.deny),
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
//            painter = painterResource(id = Res.drawable.bg_welcome_screen.drawableResId),
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
                hintText = stringResource(Res.string.email)
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
                            if (viewState.value.isPasswordVisible) {
                                Res.drawable.ic_invisible
                            } else {
                                Res.drawable.ic_visible
                            }
                        ),
                        contentDescription = null
                    )
                },
                isPassword = true,
                hintText = stringResource(Res.string.password)
            )

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.dimens.space_24),
                onClick = { onLoginClicked() },
                text = stringResource(Res.string.login)
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
                text = stringResource(Res.string.welcome_google_login)
            )

            SecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.dimens.space_16,
                        bottom = MaterialTheme.dimens.space_16
                    ),
                onClick = { onOfflineModeClicked() },
                text = stringResource(Res.string.welcome_offline_mode)
            )

            Text(
                text = stringResource(Res.string.welcome_no_account),
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
                Text(text = stringResource(Res.string.welcome_create_account))
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
