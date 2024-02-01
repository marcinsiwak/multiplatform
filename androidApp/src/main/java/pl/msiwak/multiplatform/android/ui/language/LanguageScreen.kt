package pl.msiwak.multiplatform.android.ui.language

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.theme.AppTheme
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.commonResources.MR
import pl.msiwak.multiplatform.ui.language.LanguageState

@Composable
fun LanguageScreen() {
    val viewModel = koinViewModel<pl.msiwak.multiplatform.ui.language.LanguageViewModel>()
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
            text = stringResource(MR.strings.language.resourceId),
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

@Preview
@Composable
fun LanguageScreenPreview() {
    AppTheme {
        LanguageScreenContent(MutableStateFlow(LanguageState()).collectAsState())
    }
}
