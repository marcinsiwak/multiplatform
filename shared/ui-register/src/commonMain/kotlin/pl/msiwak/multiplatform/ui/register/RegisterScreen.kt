package pl.msiwak.multiplatform.ui.register

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
import androidx.navigation.NavController
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.InputView
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.PasswordRequirements

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = koinInject()
) {
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
                errorMessage = viewState.value.loginErrorMessage?.let {
                    stringResource(
                        it
                    )
                },
                onValueChange = onLoginChanged,
                hintText = stringResource(SR.strings.email)
            )

            InputView(
                modifier = Modifier,
                value = viewState.value.password,
                errorMessage = viewState.value.passwordErrorMessage?.let {
                    stringResource(
                        it
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
                            if (viewState.value.isPasswordVisible) {
                                SR.images.ic_invisible
                            } else {
                                SR.images.ic_visible
                            }
                        ),
                        contentDescription = null
                    )
                },
                hintText = stringResource(SR.strings.password)
            )

            PasswordRequirements(requirements = viewState.value.passwordRequirements)

            MainButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.space_32),
                onClick = {
                    onRegisterClicked()
                },
                text = stringResource(SR.strings.register)
            )
        }
    }
}
//
// @Preview
// @Composable
// fun RegisterScreenPreview() {
//     AppTheme {
//         RegisterScreenContent(MutableStateFlow(RegisterState()).collectAsState())
//     }
// }
