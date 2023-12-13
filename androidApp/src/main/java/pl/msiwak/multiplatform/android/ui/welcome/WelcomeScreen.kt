package pl.msiwak.multiplatform.android.ui.welcome

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.extensions.findActivity
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.components.MainButton
import pl.msiwak.multiplatform.android.ui.components.PopupDialog
import pl.msiwak.multiplatform.android.ui.components.SecondaryButton
import pl.msiwak.multiplatform.android.ui.theme.AppTheme
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.utils.DarkLightPreview
import pl.msiwak.multiplatform.android.ui.utils.auth.GoogleAuthOneTapConfiguration
import pl.msiwak.multiplatform.commonResources.MR
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel
import pl.msiwak.multiplatform.ui.welcome.WelcomeState

@Composable
fun WelcomeScreen() {
    val viewModel = koinViewModel<WelcomeScreenViewModel>()
    val viewState = viewModel.viewState.collectAsState()
    val context = LocalContext.current.findActivity()

    val oneTapClient: SignInClient = remember {
        Identity.getSignInClient(context)
    }
    val signInRequest: BeginSignInRequest = remember {
        GoogleAuthOneTapConfiguration().signInRequest
    }

    val googleAuthContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                val idToken = credential.googleIdToken ?: return@rememberLauncherForActivityResult
                viewModel.onGoogleLogin(idToken, null)
            }
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
        onGoogleLoginClicked = {
            oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener { result ->
                    googleAuthContract.launch(
                        IntentSenderRequest
                            .Builder(result.pendingIntent.intentSender)
                            .build()
                    )
                }
                .addOnFailureListener { e ->
                    Napier.e("GOOGLE AUTH FAILED: $e")
                }
        }
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
            title = stringResource(id = MR.strings.auth_failed_title.resourceId),
            description = stringResource(id = MR.strings.auth_failed_description.resourceId),
            confirmButtonTitle = stringResource(id = MR.strings.confirm.resourceId),
            onConfirmClicked = {
                onConfirmDialogButtonClicked()
            }
        )
    }

    if (viewState.value.isSynchronizationDialogVisible) {
        PopupDialog(
            title = stringResource(id = MR.strings.synchronization_dialog_title.resourceId),
            description = stringResource(id = MR.strings.synchronization_dialog_description.resourceId),
            confirmButtonTitle = stringResource(id = MR.strings.confirm.resourceId),
            dismissButtonTitle = stringResource(id = MR.strings.deny.resourceId),
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
//            painter = painterResource(id = MR.images.bg_welcome_screen.drawableResId),
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
                hintText = stringResource(MR.strings.email.resourceId)
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
                            id = if (viewState.value.isPasswordVisible) {
                                MR.images.ic_invisible.drawableResId
                            } else {
                                MR.images.ic_visible.drawableResId
                            }
                        ),
                        contentDescription = null
                    )
                },
                isPassword = true,
                hintText = stringResource(MR.strings.password.resourceId)
            )

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.dimens.space_24),
                onClick = { onLoginClicked() },
                text = stringResource(id = MR.strings.login.resourceId)
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
                text = stringResource(id = MR.strings.welcome_google_login.resourceId)
            )

            SecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.dimens.space_16,
                        bottom = MaterialTheme.dimens.space_16
                    ),
                onClick = { onOfflineModeClicked() },
                text = stringResource(id = MR.strings.welcome_offline_mode.resourceId)
            )

            Text(
                text = stringResource(MR.strings.welcome_no_account.resourceId),
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
                Text(text = stringResource(MR.strings.welcome_create_account.resourceId))
            }
        }
    }
}

@DarkLightPreview()
@Composable
fun WelcomeScreenPreview() {
    AppTheme {
        WelcomeScreenContent(MutableStateFlow(WelcomeState()).collectAsState())
    }
}
