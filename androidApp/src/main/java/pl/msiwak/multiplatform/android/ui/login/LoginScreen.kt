package pl.msiwak.multiplatform.android.ui.login

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.koin.java.KoinJavaComponent
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.components.InputView

val viewModel: LoginViewModel by KoinJavaComponent.inject(LoginViewModel::class.java)

@Composable
fun LoginScreen() {
    val state = viewModel.loginState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.bg_welcome_screen),
            contentScale = ContentScale.Crop,
            contentDescription = "Welcome screen"
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 24.dp)
        ) {
            InputView(
                modifier = Modifier.padding(vertical = 8.dp),
                value = state.value.login,
                onValueChange = {
                    viewModel.onLoginChanged(it)
                })

            InputView(modifier = Modifier, value = state.value.password, onValueChange = {
                viewModel.onPasswordChanged(it)
            })
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp),
                onClick = {
                    viewModel.onLoginClicked()
                }) {
                Text(text = "Login")
            }
        }

    }
}