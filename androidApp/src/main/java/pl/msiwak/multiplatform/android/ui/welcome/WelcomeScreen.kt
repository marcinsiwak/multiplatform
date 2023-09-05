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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import io.github.aakira.napier.Napier
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.commonResources.MR
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.extensions.findActivity
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.components.MainButton
import pl.msiwak.multiplatform.android.ui.components.PopupDialog
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.utils.auth.GoogleAuthOneTapConfiguration
import pl.msiwak.multiplatform.core.ui.welcome.WelcomeScreenViewModel


@Composable
fun WelcomeScreen() {
    val viewModel = koinViewModel<pl.msiwak.multiplatform.core.ui.welcome.WelcomeScreenViewModel>()

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

    if (viewState.value.isErrorDialogVisible) {
        PopupDialog(
            title = "Auth failed",
            description = "Email or password is invalid",
            confirmButtonTitle = "OK",
            onConfirmClicked = {
                viewModel.onConfirmDialogButtonClicked()
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
                    top = MaterialTheme.dimens.space_164,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            InputView(
                modifier = Modifier.padding(top = MaterialTheme.dimens.space_64),
                value = viewState.value.login,
                errorMessage = viewState.value.authErrorMessage,
                onValueChange = {
                    viewModel.onLoginChanged(it)
                },
                hintText = stringResource(MR.strings.email.resourceId)
            )

            InputView(
                modifier = Modifier,
                value = viewState.value.password,
                errorMessage = viewState.value.authErrorMessage,
                onValueChange = {
                    viewModel.onPasswordChanged(it)
                },
                isPasswordVisible = viewState.value.isPasswordVisible,
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable { viewModel.onVisibilityClicked() },
                        painter = painterResource(
                            id = if (viewState.value.isPasswordVisible) {
                                R.drawable.ic_invisible
                            } else {
                                R.drawable.ic_visible
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
                    .padding(top = MaterialTheme.dimens.space_32),
                onClick = { viewModel.onLoginClicked() },
                text = stringResource(id = MR.strings.login.resourceId)
            )
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.dimens.space_24,
                        bottom = MaterialTheme.dimens.space_64
                    ),
                onClick = {
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
                },
                text = stringResource(id = MR.strings.welcome_google_login.resourceId)
            )

            Text(
                text = stringResource(MR.strings.welcome_no_account.resourceId),
                color = MaterialTheme.colorScheme.secondary
            )
            Button(
                onClick = {
                    viewModel.onRegistrationClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                )
            ) {
                Text(text = stringResource(MR.strings.welcome_create_account.resourceId))
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}