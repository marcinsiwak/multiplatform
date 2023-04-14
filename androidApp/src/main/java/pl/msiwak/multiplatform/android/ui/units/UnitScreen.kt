package pl.msiwak.multiplatform.android.ui.units

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import java.util.Locale
import org.example.library.MR
import org.koin.androidx.compose.koinViewModel
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.utils.getString
import pl.msiwak.multiplatform.ui.unit.UnitViewModel

@Composable
fun UnitScreen() {
    val viewModel = koinViewModel<UnitViewModel>()
    val state = viewModel.viewState.collectAsState()
    val dimens = LocalDim.current

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Text(
            modifier = Modifier.padding(vertical = dimens.space_16, horizontal = dimens.space_24),
            text = getString(context, MR.strings.settings_unit),
            fontSize = dimens.font_24,
            color = Color.White
        )

        LazyColumn {
            itemsIndexed(state.value.unitItemList) { index, item ->
                Row {
                    RadioButton(
                        selected = item.isSelected,
                        onClick = {
                            viewModel.onUnitTypeChanged(index)
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.White,
                            unselectedColor = Color.LightGray
                        )
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = item.unitType.name.lowercase()
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}
