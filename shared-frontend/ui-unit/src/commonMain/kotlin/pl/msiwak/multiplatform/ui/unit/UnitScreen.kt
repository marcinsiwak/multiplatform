package pl.msiwak.multiplatform.ui.unit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared_frontend.commonresources.generated.resources.Res
import athletetrack.shared_frontend.commonresources.generated.resources.settings_unit
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

@Composable
fun UnitScreen(
    navController: NavController,
    viewModel: UnitViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    UnitScreenContent(
        navController = navController,
        viewState = viewState,
        onUiAction = viewModel::onUiAction
    )
}

@Composable
fun UnitScreenContent(
    navController: NavController,
    viewState: State<UnitState>,
    onUiAction: (UnitUiAction) -> Unit
) {
    Scaffold(
        topBar = {
            AppBar(navController = navController, title = stringResource(Res.string.settings_unit))
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary
                            )
                        )
                    )
                    .padding(top = it.calculateTopPadding())
            ) {
                LazyColumn {
                    itemsIndexed(viewState.value.unitItemList) { index, item ->
                        Row {
                            RadioButton(
                                selected = item.isSelected,
                                onClick = {
                                    onUiAction(UnitUiAction.OnUnitTypeChanged(index))
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                                    unselectedColor = Color.LightGray
                                )
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = item.unitType.name.lowercase()
                                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                                color = MaterialTheme.colorScheme.onPrimary,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    )
}

@DarkLightPreview
@Composable
fun UnitScreenPreview() {
    AppTheme {
        UnitScreenContent(
            rememberNavController(),
            MutableStateFlow(UnitState()).collectAsState()
        ) {}
    }
}
