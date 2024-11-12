package pl.msiwak.multiplatform.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.accept_terms
import athletetrack.shared.commonresources.generated.resources.email
import athletetrack.shared.commonresources.generated.resources.ic_invisible
import athletetrack.shared.commonresources.generated.resources.ic_visible
import athletetrack.shared.commonresources.generated.resources.password
import athletetrack.shared.commonresources.generated.resources.register
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.InputView
import pl.msiwak.multiplatform.ui.commonComponent.Loader
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.MainCheckbox
import pl.msiwak.multiplatform.ui.commonComponent.PasswordRequirements
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

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
        onUiAction = viewModel::onUiAction
    )
}

@Composable
fun RegisterScreenContent(
    navController: NavController,
    viewState: State<RegisterState>,
    onUiAction: (RegisterUiAction) -> Unit
) {
    if (viewState.value.isLoading) {
        Loader()
    }

    Scaffold(
        topBar = {
            AppBar(navController = navController, title = stringResource(Res.string.register))
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary
                            )
                        )
                    )
                    .padding(
                        start = MaterialTheme.dimens.space_36,
                        end = MaterialTheme.dimens.space_36
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                InputView(
                    modifier = Modifier.padding(top = MaterialTheme.dimens.space_64),
                    value = viewState.value.login,
                    errorMessage = viewState.value.loginErrorMessage?.let {
                        stringResource(
                            it
                        )
                    },
                    onValueChange = { onUiAction(RegisterUiAction.OnLoginChanged(it)) },
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
                    onValueChange = { onUiAction(RegisterUiAction.OnPasswordChanged(it)) },
                    isPassword = true,
                    isPasswordVisible = viewState.value.isPasswordVisible,
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .clickable { onUiAction(RegisterUiAction.OnVisibilityClicked) },
                            painter = painterResource(
                                if (viewState.value.isPasswordVisible) {
                                    Res.drawable.ic_invisible
                                } else {
                                    Res.drawable.ic_visible
                                }
                            ),
                            tint = MaterialTheme.colorScheme.onTertiary,
                            contentDescription = null
                        )
                    },
                    hintText = stringResource(Res.string.password)
                )

                PasswordRequirements(requirements = viewState.value.passwordRequirements)

                MainCheckbox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.dimens.space_24),
                    checked = viewState.value.isTermsChecked,
                    onCheckedChange = { onUiAction(RegisterUiAction.OnTermsClicked(it)) },
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append(stringResource(Res.string.accept_terms))
                        }
                    },
                    onTextClicked = {
                        navController.navigate(NavDestination.TermsDestination.NavTermsScreen.route)
                    }
                )

                MainButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.dimens.space_32),
                    enabled = with(viewState.value) {
                        isTermsChecked && login.isNotBlank() && passwordErrorMessage == null
                    },
                    onClick = {
                        onUiAction(RegisterUiAction.OnRegisterClicked)
                    },
                    text = stringResource(Res.string.register)
                )
            }
        }
    )
}

@DarkLightPreview
@Composable
fun RegisterScreenPreview() {
    AppTheme {
        RegisterScreenContent(
            rememberNavController(),
            MutableStateFlow(RegisterState()).collectAsState(),
            onUiAction = {}
        )
    }
}
