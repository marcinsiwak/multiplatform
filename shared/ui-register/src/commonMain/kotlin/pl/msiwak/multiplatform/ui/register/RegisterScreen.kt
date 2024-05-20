@file:OptIn(ExperimentalResourceApi::class)

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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.add_category
import athletetrack.shared.commonresources.generated.resources.email
import athletetrack.shared.commonresources.generated.resources.ic_invisible
import athletetrack.shared.commonresources.generated.resources.ic_visible
import athletetrack.shared.commonresources.generated.resources.password
import athletetrack.shared.commonresources.generated.resources.register
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
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

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collectLatest { event ->
            when (event) {
                RegisterEvent.NavigateToVerifyEmail -> navController.navigate(
                    NavDestination.VerifyEmailDestination.NavVerifyEmailScreen.route
                )
            }
        }
    }

    RegisterScreenContent(
        navController = navController,
        viewState = viewState,
        onLoginChanged = viewModel::onLoginChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onVisibilityClicked = viewModel::onVisibilityClicked,
        onRegisterClicked = viewModel::onRegisterClicked
    )
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RegisterScreenContent(
    navController: NavController,
    viewState: State<RegisterState>,
    onLoginChanged: (String) -> Unit = { _ -> },
    onPasswordChanged: (String) -> Unit = { _ -> },
    onVisibilityClicked: () -> Unit = {},
    onRegisterClicked: () -> Unit = {}
) {
    if (viewState.value.isLoading) {
        Loader()
    }


    Scaffold(
        topBar = {
            AppBar(navController = navController, title = stringResource(Res.string.register))
        },
        content = {

            Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            painter = painterResource(id = Res.drawable.bg_running_field.drawableResId),
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
                        hintText = stringResource(Res.string.email)
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
                                        Res.drawable.ic_invisible
                                    } else {
                                        Res.drawable.ic_visible
                                    }
                                ),
                                contentDescription = null
                            )
                        },
                        hintText = stringResource(Res.string.password)
                    )

                    PasswordRequirements(requirements = viewState.value.passwordRequirements)

                    MainButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.dimens.space_32),
                        onClick = {
                            onRegisterClicked()
                        },
                        text = stringResource(Res.string.register)
                    )
                }
            }
        }
    )
}
//
// @Preview
// @Composable
// fun RegisterScreenPreview() {
//     AppTheme {
//         RegisterScreenContent(MutableStateFlow(RegisterState()).collectAsState())
//     }
// }
