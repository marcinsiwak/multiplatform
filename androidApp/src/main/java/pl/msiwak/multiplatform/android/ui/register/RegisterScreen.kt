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
import androidx.compose.ui.unit.dp
import org.example.library.MR
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.utils.getString
import pl.msiwak.multiplatform.ui.register.RegisterViewModel

val viewModel: RegisterViewModel by inject(RegisterViewModel::class.java)

@Composable
fun RegisterScreen() {
    val state = viewModel.registerState.collectAsState()
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
                .padding(vertical = 24.dp)
        ) {
            InputView(
                modifier = Modifier.padding(vertical = 8.dp),
                value = state.value.login,
                errorMessage = state.value.loginErrorMessage,
                onValueChange = {
                    viewModel.onLoginChanged(it)
                },
                hintText = getString(LocalContext.current, MR.strings.email)
            )

            InputView(
                modifier = Modifier,
                value = state.value.password,
                errorMessage = state.value.passwordErrorMessage,
                onValueChange = {
                    viewModel.onPasswordChanged(it)
                },
                isPassword = true,
                hintText = getString(LocalContext.current, MR.strings.password)
            )
            Button(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(vertical = 16.dp),
                onClick = {
                    viewModel.onRegisterClicked()
                }) {
                Text(text = getString(LocalContext.current, MR.strings.register))
            }
        }

    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}