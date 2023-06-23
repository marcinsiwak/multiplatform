package pl.msiwak.multiplatform.android.ui.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel

@Composable
fun WelcomeScreen() {
    val viewModel: WelcomeScreenViewModel by inject(WelcomeScreenViewModel::class.java)

    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            painter = painterResource(id = MR.images.bg_welcome_screen.drawableResId),
//            contentScale = ContentScale.Crop,
//            contentDescription = "Welcome screen"
//        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = MaterialTheme.dimens.space_36, vertical = MaterialTheme.dimens.space_96)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_color)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.space_8),
                onClick = { viewModel.onRegistrationClicked() }) {
                Text(text = "Register")
            }
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.button_color_two)),
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.onLoginClicked() }) {
                Text(text = "Login")
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}