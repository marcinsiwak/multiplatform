package pl.msiwak.multiplatform.android.ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.java.KoinJavaComponent.inject

val viewModel: RegisterViewModel by inject(RegisterViewModel::class.java)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    val state = viewModel.registerState.collectAsState()
    Column() {
        TextField(modifier = Modifier, value = state.value.login, onValueChange = {

            viewModel.onLoginChanged(it)
        })

        TextField(modifier = Modifier, value = state.value.password, onValueChange = {
            viewModel.onPasswordChanged(it)
        })
        Button(onClick = {
            viewModel.onRegisterClicked()
        }) {
            Text(text = "Register")
        }
    }


}