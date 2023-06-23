package pl.msiwak.multiplatform.android.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.example.library.MR
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.ui.register.RegisterViewModel


@Composable
fun RegisterScreen() {
    val viewModel: RegisterViewModel by inject(RegisterViewModel::class.java)

    val state = viewModel.registerState.collectAsState()
    val dimens = LocalDim.current
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = MR.images.bg_running_field.drawableResId),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = dimens.space_24)
        ) {
            InputView(
                modifier = Modifier.padding(vertical = dimens.space_8),
                value = state.value.login,
                errorMessage = state.value.loginErrorMessage,
                onValueChange = {
                    viewModel.onLoginChanged(it)
                },
                hintText = stringResource(MR.strings.email.resourceId)
            )

            InputView(
                modifier = Modifier,
                value = state.value.password,
                errorMessage = state.value.passwordErrorMessage,
                onValueChange = {
                    viewModel.onPasswordChanged(it)
                },
                isPassword = true,
                hintText = stringResource(MR.strings.password.resourceId)
            )
            Button(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(vertical = dimens.space_16),
                onClick = {
                    viewModel.onRegisterClicked()
                }) {
                Text(text = stringResource(MR.strings.register.resourceId))
            }
        }

    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}