package pl.msiwak.multiplatform.android.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.MR
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.components.MainButton
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.ui.login.LoginViewModel


@Composable
fun LoginScreen() {
    val viewModel = koinViewModel<LoginViewModel>()

    val state = viewModel.loginState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = MR.images.bg_running_field.drawableResId),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .align(Alignment.Center)
                .padding(
                    vertical = MaterialTheme.dimens.space_24,
                    horizontal = MaterialTheme.dimens.space_36
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputView(
                modifier = Modifier.padding(vertical = MaterialTheme.dimens.space_8),
                value = state.value.login,
                onValueChange = {
                    viewModel.onLoginChanged(it)
                },
                hintText = stringResource(MR.strings.email.resourceId)
            )

            InputView(
                modifier = Modifier,
                value = state.value.password,
                errorMessage = state.value.authErrorMessage,
                onValueChange = {
                    viewModel.onPasswordChanged(it)
                },
                isPassword = true,
                hintText = stringResource(MR.strings.password.resourceId)
            )
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.space_16),
                onClick = {
                    viewModel.onLoginClicked()
                },
                text = stringResource(MR.strings.login.resourceId)
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
