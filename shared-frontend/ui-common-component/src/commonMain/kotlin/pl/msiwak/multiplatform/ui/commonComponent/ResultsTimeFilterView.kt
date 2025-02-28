package pl.msiwak.multiplatform.ui.commonComponent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import pl.msiwak.multiplatform.commonObject.DateFilterType
import pl.msiwak.multiplatform.commonResources.theme.dimens
import pl.msiwak.multiplatform.ui.commonComponent.extension.fittingTabIndicatorOffset

@Composable
fun ResultsTimeFilterView(
    modifier: Modifier = Modifier,
    tabs: List<DateFilterType>,
    selectedPos: Int = 0,
    onTabClicked: (DateFilterType) -> Unit
) {
    val density = LocalDensity.current
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
            SecondaryIndicator(
                Modifier
                    .fittingTabIndicatorOffset(
                        currentTabPosition = it[selectedPos],
                        tabWidth = tabWidths[selectedPos]
                    ),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        divider = {},
        containerColor = Color.Transparent,
        selectedTabIndex = selectedPos,
        tabs = {
            tabs.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier
                        .padding(
                            vertical = MaterialTheme.dimens.space_12
                        ),
                    selected = true,
                    onClick = {
                        onTabClicked(item)
                    }
                ) {
                    Text(
                        text = stringResource(item.nameResourceId),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium,
                        onTextLayout = { textLayoutResult ->
                            tabWidths[index] =
                                with(density) { textLayoutResult.size.width.toDp() }
                        }
                    )
                }
            }
        }
    )
}
