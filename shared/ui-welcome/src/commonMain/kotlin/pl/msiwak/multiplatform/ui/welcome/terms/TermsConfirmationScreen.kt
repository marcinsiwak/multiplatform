package pl.msiwak.multiplatform.ui.welcome.terms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.confirm
import athletetrack.shared.commonresources.generated.resources.deny
import athletetrack.shared.commonresources.generated.resources.synchronization_dialog_description
import athletetrack.shared.commonresources.generated.resources.synchronization_dialog_title
import athletetrack.shared.commonresources.generated.resources.terms
import athletetrack.shared.commonresources.generated.resources.terms_confirmation_description
import athletetrack.shared.commonresources.generated.resources.terms_confirmation_title
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.navigator.destination.NavDestination
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.MainButton
import pl.msiwak.multiplatform.ui.commonComponent.PopupDialog
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

@Composable
fun TermsConfirmationScreen(
    idToken: String,
    accessToken: String?,
    navController: NavController,
    viewModel: TermsConfirmationViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collectLatest { event ->
            when (event) {
                is TermsConfirmationEvent.NavigateToDashboard -> navController.navigate(
                    NavDestination.DashboardDestination.NavDashboardScreen.route
                )
            }
        }
    }

    TermsConfirmationContent(
        navController,
        viewState,
        onButtonClick = {
            viewModel.onGoogleLogin(idToken, accessToken)
        },
        onTermsClick = {
            navController.navigate(NavDestination.TermsDestination.NavTermsScreen.route)
        },
        onConfirmSynchronizationClicked = viewModel::onConfirmSynchronizationClicked,
        onDismissSynchronizationClicked = viewModel::onDismissSynchronizationClicked
    )
}

@Composable
private fun TermsConfirmationContent(
    navController: NavController,
    viewState: State<TermsConfirmationState>,
    onButtonClick: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onConfirmSynchronizationClicked: () -> Unit = {},
    onDismissSynchronizationClicked: () -> Unit = {}
) {
    if (viewState.value.isSynchronizationDialogVisible) {
        PopupDialog(
            title = stringResource(Res.string.synchronization_dialog_title),
            description = stringResource(Res.string.synchronization_dialog_description),
            confirmButtonTitle = stringResource(Res.string.confirm),
            dismissButtonTitle = stringResource(Res.string.deny),
            onConfirmClicked = {
                onConfirmSynchronizationClicked()
            },
            onDismissClicked = {
                onDismissSynchronizationClicked()
            }
        )
    }

    Scaffold(
        topBar = {
            AppBar(
                navController = navController,
                title = stringResource(Res.string.terms_confirmation_title)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.dimens.space_8),
                    text = stringResource(Res.string.terms_confirmation_description),
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            onTermsClick()
                        }
                        .padding(vertical = MaterialTheme.dimens.space_8),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                            append(stringResource(Res.string.terms))
                        }
                    },
                    style = MaterialTheme.typography.labelLarge
                )

                Spacer(modifier = Modifier.weight(1f))

                MainButton(
                    modifier = Modifier.padding(MaterialTheme.dimens.space_16),
                    text = stringResource(Res.string.terms_confirmation_title),
                    onClick = onButtonClick
                )
            }
        }
    )
}

@DarkLightPreview
@Composable
private fun TermsConfirmationPreview() {
    AppTheme {
        TermsConfirmationScreen(
            navController = rememberNavController(),
            accessToken = "",
            idToken = ""
        )
    }
}
