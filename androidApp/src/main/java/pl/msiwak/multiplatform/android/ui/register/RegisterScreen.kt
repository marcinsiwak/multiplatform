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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.components.InputView
import pl.msiwak.multiplatform.android.ui.components.MainButton
import pl.msiwak.multiplatform.android.ui.components.PasswordRequirements
import pl.msiwak.multiplatform.android.ui.loader.Loader
import pl.msiwak.multiplatform.android.ui.theme.AppTheme
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.ui.register.RegisterState
import pl.msiwak.multiplatform.ui.register.RegisterViewModel

@Composable
fun RegisterScreen() {
    val viewModel = koinViewModel<RegisterViewModel>()

    val viewState = viewModel.viewState.collectAsState()

    RegisterScreenContent(
        viewState = viewState,
        onLoginChanged = viewModel::onLoginChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onVisibilityClicked = viewModel::onVisibilityClicked,
        onRegisterClicked = viewModel::onRegisterClicked
    )
}

@Composable
fun RegisterScreenContent(
    viewState: State<RegisterState>,
    onLoginChanged: (String) -> Unit = { _ -> },
    onPasswordChanged: (String) -> Unit = { _ -> },
    onVisibilityClicked: () -> Unit = {},
    onRegisterClicked: () -> Unit = {}
) {
    if (viewState.value.isLoading) {
        Loader()
    }

    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            painter = painterResource(id = SR.images.bg_running_field.drawableResId),
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
                    top = MaterialTheme.dimens.space_164
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            InputView(
                modifier = Modifier.padding(top = MaterialTheme.dimens.space_64),
                value = viewState.value.login,
                errorMessage = viewState.value.loginErrorMessage?.resourceId?.let {
                    stringResource(
                        id = it
                    )
                },
                onValueChange = onLoginChanged,
                hintText = stringResource(SR.strings.email.resourceId)
            )

            InputView(
                modifier = Modifier,
                value = viewState.value.password,
                errorMessage = viewState.value.passwordErrorMessage?.resourceId?.let {
                    stringResource(
                        id = it
                    )
                },
                onValueChange = onPasswordChanged,
                isPassword = true,
                isPasswordVisible = viewState.value.isPasswordVisible,
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable { onVisibilityClicked() },
                        painter = painterResource(
                            id = if (viewState.value.isPasswordVisible) {
                                SR.images.ic_invisible.drawableResId
                            } else {
                                SR.images.ic_visible.drawableResId
                            }
                        ),
                        contentDescription = null
                    )
                },
                hintText = stringResource(SR.strings.password.resourceId)
            )

            PasswordRequirements(requirements = viewState.value.passwordRequirements)

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.space_32),
                onClick = {
                    onRegisterClicked()
                },
                text = stringResource(SR.strings.register.resourceId)
            )
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    AppTheme {
        RegisterScreenContent(MutableStateFlow(RegisterState()).collectAsState())
    }
}
