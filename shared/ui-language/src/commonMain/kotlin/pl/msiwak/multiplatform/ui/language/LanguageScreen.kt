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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import dev.icerock.moko.resources.compose.stringResource
import org.koin.compose.koinInject
import pl.msiwak.multiplatform.commonResources.SR
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.commonResources.theme.font

@Composable
fun LanguageScreen(
    navController: NavController,
    viewModel: LanguageViewModel = koinInject()
) {
    val viewState = viewModel.viewState.collectAsState()

    LanguageScreenContent(
        viewState = viewState,
        onLanguageChanged = viewModel::onLanguageChanged
    )
}

@Composable
fun LanguageScreenContent(
    viewState: State<LanguageState>,
    onLanguageChanged: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = MaterialTheme.dimens.space_16,
                horizontal = MaterialTheme.dimens.space_24
            ),
            text = stringResource(SR.strings.language),
            fontSize = MaterialTheme.font.font_24,
            color = MaterialTheme.colorScheme.onPrimary
        )

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

// @Preview
// @Composable
// fun LanguageScreenPreview() {
//     AppTheme {
//         LanguageScreenContent(MutableStateFlow(LanguageState()).collectAsState())
//     }
// }
