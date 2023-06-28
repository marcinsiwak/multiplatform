package pl.msiwak.multiplatform.android.ui.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.java.KoinJavaComponent.inject
import pl.msiwak.multiplatform.android.ui.components.MainButton
import pl.msiwak.multiplatform.android.ui.components.SecondaryButton
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.ui.welcome.WelcomeScreenViewModel
import pl.msiwak.multiplatform.MR

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
                .padding(
                    horizontal = MaterialTheme.dimens.space_36,
                    vertical = MaterialTheme.dimens.space_96
                )
        ) {
            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.space_8),
                onClick = { viewModel.onRegistrationClicked() },
                text = stringResource(id = MR.strings.register.resourceId)
            )
            SecondaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.onLoginClicked() },
                text = stringResource(id = MR.strings.login.resourceId)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}