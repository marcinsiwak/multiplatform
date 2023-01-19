package pl.msiwak.multiplatform.android.ui.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.java.KoinJavaComponent.inject

@Composable
fun WelcomeScreen() {
    val viewModel: WelcomeScreenViewModel by inject(WelcomeScreenViewModel::class.java)
    Column {
        Text(text = "Welcome screen: ${viewModel.title}")
        Button(onClick = { viewModel.navigateToRegistration() }) {
            Text(text = "OPEN REGISTRATION")
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}