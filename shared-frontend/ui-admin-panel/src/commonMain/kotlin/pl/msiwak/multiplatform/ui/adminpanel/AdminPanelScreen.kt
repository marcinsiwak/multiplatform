package pl.msiwak.multiplatform.ui.adminpanel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared_frontend.commonresources.generated.resources.Res
import athletetrack.shared_frontend.commonresources.generated.resources.admin_panel
import athletetrack.shared_frontend.commonresources.generated.resources.email
import athletetrack.shared_frontend.commonresources.generated.resources.language
import athletetrack.shared_frontend.commonresources.generated.resources.user_type
import athletetrack.shared_frontend.commonresources.generated.resources.username
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.commonResources.theme.color
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.extension.verticalBorder
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

@Composable
fun AdminPanelScreen(
    navController: NavController,
    viewModel: AdminPanelViewModel = koinViewModel<AdminPanelViewModel>()
) {
    val viewState = viewModel.viewState.collectAsState()

    AdminPanelScreenContent(
        navController = navController,
        viewState = viewState,
        onUiAction = viewModel::onUiAction
    )
}

@Composable
fun AdminPanelScreenContent(
    navController: NavController,
    viewState: State<AdminPanelState>,
    onUiAction: (AdminPanelUiAction) -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(navController = navController, title = stringResource(Res.string.admin_panel))
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TableCell(
                        text = stringResource(Res.string.email),
                    )
                    TableCell(
                        text = stringResource(Res.string.username),
                    )
                    TableCell(
                        text = stringResource(Res.string.user_type),
                    )
                }

                HorizontalDivider(thickness = MaterialTheme.dimens.border_width, color = MaterialTheme.colorScheme.onPrimary)

                LazyColumn {
                    items(viewState.value.users) { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TableCell(
                                text = item.email,
                            )
                            TableCell(
                                text = item.userName,
                            )
                            TableCell(
                                text = item.role.name,
                            )
                        }
                        HorizontalDivider()
                    }
                }
            }
        }
    )
}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float = 1f
) {
    Text(
        text = text,
        modifier = Modifier
            .verticalBorder(MaterialTheme.dimens.border_width, MaterialTheme.colorScheme.onPrimary)
            .weight(weight)
            .padding(MaterialTheme.dimens.space_8)
    )
}

@DarkLightPreview
@Composable
fun AdminPanelScreenPreview() {
    AppTheme {
        AdminPanelScreenContent(
            navController = rememberNavController(),
            MutableStateFlow(AdminPanelState()).collectAsState()
        ) {}
    }
}
