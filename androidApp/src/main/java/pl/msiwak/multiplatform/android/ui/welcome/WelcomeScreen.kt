package pl.msiwak.multiplatform.android.ui.welcome

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.MR
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.components.MainButton
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel

@Composable
fun WelcomeScreen() {
    val viewModel = koinViewModel<WelcomeScreenViewModel>()

    val viewState = viewModel.viewState.collectAsState()

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
                modifier = Modifier.padding(vertical = MaterialTheme.dimens.space_8),
                value = viewState.value.login,
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
                isPassword = true,
                hintText = stringResource(MR.strings.password.resourceId)
            )
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.space_32),
                onClick = { viewModel.onLoginClicked() },
                text = stringResource(id = MR.strings.login.resourceId)
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