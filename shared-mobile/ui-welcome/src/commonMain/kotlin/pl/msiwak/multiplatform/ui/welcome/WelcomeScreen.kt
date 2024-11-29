package pl.msiwak.multiplatform.ui.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import athletetrack.shared_mobile.commonresources.generated.resources.Res
import athletetrack.shared_mobile.commonresources.generated.resources.auth_failed_description
import athletetrack.shared_mobile.commonresources.generated.resources.auth_failed_title
import athletetrack.shared_mobile.commonresources.generated.resources.confirm
import athletetrack.shared_mobile.commonresources.generated.resources.deny
import athletetrack.shared_mobile.commonresources.generated.resources.email
import athletetrack.shared_mobile.commonresources.generated.resources.ic_google
import athletetrack.shared_mobile.commonresources.generated.resources.ic_invisible
import athletetrack.shared_mobile.commonresources.generated.resources.ic_visible
import athletetrack.shared_mobile.commonresources.generated.resources.login
import athletetrack.shared_mobile.commonresources.generated.resources.password
import athletetrack.shared_mobile.commonresources.generated.resources.synchronization_dialog_description
import athletetrack.shared_mobile.commonresources.generated.resources.synchronization_dialog_title
import athletetrack.shared_mobile.commonresources.generated.resources.welcome_create_account
import athletetrack.shared_mobile.commonresources.generated.resources.welcome_google_login
import athletetrack.shared_mobile.commonresources.generated.resources.welcome_no_account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.ui.commonComponent.InputView
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.rememberGoogleLoginLauncherForActivityResult
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

@Composable
fun WelcomeScreen(
    navController: NavController,
    viewModel: WelcomeScreenViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    val startSignIn = rememberGoogleLoginLauncherForActivityResult(
        onResultOk = { idToken, accessToken ->
            viewModel.onUiAction(WelcomeUiAction.OnGoogleLoginSucceed(idToken, accessToken))
        }
    )

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collectLatest { event ->
            when (event) {
                is WelcomeEvent.NavigateToDashboard -> navController.navigate(
                    NavDestination.DashboardDestination.NavDashboardScreen.route
                )

                WelcomeEvent.NavigateToRegistration -> navController.navigate(
                    NavDestination.RegistrationDestination.NavRegistrationScreen.route
                )

                WelcomeEvent.NavigateToVerifyEmail -> navController.navigate(
                    NavDestination.VerifyEmailDestination.NavVerifyEmailScreen.route
                )

                is WelcomeEvent.NavigateToTermsAndConditions -> navController.navigate(
                    NavDestination.WelcomeDestination.NavTermsConfirmationScreen.route(
                        event.uuid
                    )
                )
            }
        }
    }

    WelcomeScreenContent(
        viewState = viewState,
        onUiAction = {
            when (it) {
                WelcomeUiAction.OnGoogleLoginClicked -> startSignIn()
                else -> viewModel.onUiAction(it)
            }
        }
    )
}

@Composable
fun WelcomeScreenContent(
    viewState: State<WelcomeState>,
    onUiAction: (WelcomeUiAction) -> Unit

) {
    if (viewState.value.isErrorDialogVisible) {
        PopupDialog(
            title = stringResource(Res.string.auth_failed_title),
            description = stringResource(Res.string.auth_failed_description),
            confirmButtonTitle = stringResource(Res.string.confirm),
            onConfirmClicked = {
                onUiAction(WelcomeUiAction.OnConfirmDialogButtonClicked)
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
                onUiAction(WelcomeUiAction.OnConfirmSynchronizationClicked)
            },
            onDismissClicked = {
                onUiAction(WelcomeUiAction.OnDismissSynchronizationClicked)
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary
                            )
                        )
                    )
                    .padding(
                        start = MaterialTheme.dimens.space_36,
                        end = MaterialTheme.dimens.space_36
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                InputView(
                    modifier = Modifier.padding(top = MaterialTheme.dimens.space_64),
                    value = viewState.value.login,
                    errorMessage = viewState.value.authErrorMessage,
                    onValueChange = {
                        onUiAction(WelcomeUiAction.OnLoginChanged(it))
                    },
                    hintText = stringResource(Res.string.email)
                )

                InputView(
                    modifier = Modifier,
                    value = viewState.value.password,
                    errorMessage = viewState.value.authErrorMessage,
                    onValueChange = {
                        onUiAction(WelcomeUiAction.OnPasswordChanged(it))
                    },
                    isPasswordVisible = viewState.value.isPasswordVisible,
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .clickable { onUiAction(WelcomeUiAction.OnVisibilityClicked) },
                            painter = painterResource(
                                if (viewState.value.isPasswordVisible) {
                                    Res.drawable.ic_invisible
                                } else {
                                    Res.drawable.ic_visible
                                }
                            ),
                            tint = MaterialTheme.colorScheme.onTertiary,
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
                    onClick = { onUiAction(WelcomeUiAction.OnLoginClicked) },
                    text = stringResource(Res.string.login)
                )

                MainButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = MaterialTheme.dimens.space_16
                        ),
                    onClick = {
                        onUiAction(WelcomeUiAction.OnGoogleLoginClicked)
                    },
                    leadingIcon = Res.drawable.ic_google,
                    text = stringResource(Res.string.welcome_google_login)
                )

                // todo: improve offline mode - currently feature is disabled
//                SecondaryButton(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(
//                            top = MaterialTheme.dimens.space_16,
//                            bottom = MaterialTheme.dimens.space_16
//                        ),
//                    onClick = { onUiAction(WelcomeUiAction.OnOfflineModeClicked) },
//                    text = stringResource(Res.string.welcome_offline_mode)
//                )

                Text(
                    modifier = Modifier.padding(top = MaterialTheme.dimens.space_16),
                    text = stringResource(Res.string.welcome_no_account),
                    color = MaterialTheme.colorScheme.secondary
                )
                Button(
                    onClick = {
                        onUiAction(WelcomeUiAction.OnRegistrationClicked)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = stringResource(Res.string.welcome_create_account))
                }
            }
        }
    )
}

@DarkLightPreview()
@Composable
fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreenContent(MutableStateFlow(WelcomeState()).collectAsState()) {}
    }
}
