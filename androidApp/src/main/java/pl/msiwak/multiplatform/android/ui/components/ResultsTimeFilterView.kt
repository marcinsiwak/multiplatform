package pl.msiwak.multiplatform.android.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import pl.msiwak.multiplatform.android.extensions.fittingTabIndicatorOffset
import pl.msiwak.multiplatform.android.ui.theme.LocalDim
import pl.msiwak.multiplatform.android.ui.utils.getString
import pl.msiwak.multiplatform.data.common.DateFilter

@Composable
fun ResultsTimeFilterView(
    modifier: Modifier = Modifier,
    tabs: List<DateFilter>,
    selectedPos: Int = 0,
    onTabClicked: (Int) -> Unit
) {
    val density = LocalDensity.current
    val dimen = LocalDim.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    TabRow(
        modifier = modifier,
        indicator = {
            TabRowDefaults.Indicator(
                Modifier
                    .fittingTabIndicatorOffset(
                        currentTabPosition = it[selectedPos],
                        tabWidth = tabWidths[selectedPos]
                    ),
                color = Color.White
            )
        },
        divider = {},
        containerColor = Color.Black,
        selectedTabIndex = selectedPos,
        tabs = {
            tabs.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier.padding(
                        vertical = dimen.space_12,
                        horizontal = 0.dp
                    ),
                    selected = true,
                    onClick = {
                        onTabClicked(index)
                    }) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = 0.dp
                        ),
                        text = getString(LocalContext.current, item.type.nameResourceId), color = Color.White,
                        onTextLayout = { textLayoutResult ->
                            tabWidths[index] =
                                with(density) { textLayoutResult.size.width.toDp() }
                        })
                }
            }
        })
}