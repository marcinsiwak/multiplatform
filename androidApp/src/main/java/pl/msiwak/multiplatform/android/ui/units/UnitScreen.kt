package pl.msiwak.multiplatform.android.ui.units

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.theme.AppTheme
import pl.msiwak.multiplatform.android.ui.theme.dimens
import pl.msiwak.multiplatform.android.ui.theme.font
import pl.msiwak.multiplatform.android.ui.utils.DarkLightPreview
import pl.msiwak.multiplatform.commonResources.MR
import pl.msiwak.multiplatform.ui.unit.UnitState
import java.util.Locale

@Composable
fun UnitScreen() {
    val viewModel = koinViewModel<pl.msiwak.multiplatform.ui.unit.UnitViewModel>()
    val viewState = viewModel.viewState.collectAsState()

    UnitScreenContent(
        viewState = viewState,
        onUnitTypeChanged = viewModel::onUnitTypeChanged
    )
}

@Composable
fun UnitScreenContent(
    viewState: State<UnitState>,
    onUnitTypeChanged: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = MaterialTheme.dimens.space_16,
                horizontal = MaterialTheme.dimens.space_24
            ),
            text = stringResource(MR.strings.settings_unit.resourceId),
            fontSize = MaterialTheme.font.font_24,
            color = Color.White
        )

        LazyColumn {
            itemsIndexed(viewState.value.unitItemList) { index, item ->
                Row {
                    RadioButton(
                        selected = item.isSelected,
                        onClick = {
                            onUnitTypeChanged(index)
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedColor = Color.LightGray
                        )
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = item.unitType.name.lowercase()
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@DarkLightPreview
@Composable
fun UnitScreenPreview() {
    AppTheme {
        UnitScreenContent(MutableStateFlow(UnitState()).collectAsState())
    }
}
