package pl.msiwak.multiplatform.ui.language

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
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import athletetrack.shared.commonresources.generated.resources.Res
import athletetrack.shared.commonresources.generated.resources.language
import kotlinx.coroutines.flow.MutableStateFlow
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.theme.AppTheme
import pl.msiwak.multiplatform.ui.commonComponent.AppBar
import pl.msiwak.multiplatform.ui.commonComponent.util.DarkLightPreview

@Composable
fun LanguageScreen(
    navController: NavController,
    viewModel: LanguageViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    LanguageScreenContent(
        navController = navController,
        viewState = viewState,
        onLanguageChanged = viewModel::onLanguageChanged
    )
}

@Composable
fun LanguageScreenContent(
    navController: NavController,
    viewState: State<LanguageState>,
    onLanguageChanged: (Int) -> Unit = {}
) {
    Scaffold(
        topBar = {
            AppBar(navController = navController, title = stringResource(Res.string.language))
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
            ) {
                LazyColumn {
                    itemsIndexed(viewState.value.languages) { index, item ->
                        Row {
                            RadioButton(
                                selected = item.isSelected,
                                onClick = {
                                    onLanguageChanged(index)
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                                    unselectedColor = MaterialTheme.colorScheme.tertiary
                                )
                            )
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = item.name,
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
fun LanguageScreenPreview() {
    AppTheme {
        LanguageScreenContent(
            navController = rememberNavController(),
            MutableStateFlow(LanguageState()).collectAsState()
        ) {}
    }
}
