package pl.msiwak.multiplatform.android.ui.register

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.MR
import pl.msiwak.multiplatform.android.R
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.components.MainButton
import pl.msiwak.multiplatform.android.ui.components.PasswordRequirements
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.core.ui.register.RegisterViewModel

@Composable
fun RegisterScreen() {
    val viewModel = koinViewModel<pl.msiwak.multiplatform.core.ui.register.RegisterViewModel>()

    val viewState = viewModel.viewState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            painter = painterResource(id = MR.images.bg_running_field.drawableResId),
//            contentScale = ContentScale.Crop,
//            contentDescription = null
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
                errorMessage = viewState.value.loginErrorMessage?.resourceId?.let { stringResource(id = it) },
                onValueChange = {
                    viewModel.onLoginChanged(it)
                },
                hintText = stringResource(MR.strings.email.resourceId)
            )

            InputView(
                modifier = Modifier,
                value = viewState.value.password,
                errorMessage = viewState.value.passwordErrorMessage?.resourceId?.let { stringResource(id = it) },
                onValueChange = {
                    viewModel.onPasswordChanged(it)
                },
                isPassword = true,
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
                hintText = stringResource(MR.strings.password.resourceId)
            )

            PasswordRequirements(requirements = viewState.value.passwordRequirements)

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.space_32),
                onClick = {
                    viewModel.onRegisterClicked()
                },
                text = stringResource(MR.strings.register.resourceId)
            )
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}