package pl.msiwak.multiplatform.ui.unit

import pl.msiwak.multiplatform.data.common.UnitItem
import pl.msiwak.multiplatform.data.common.UnitType

data class UnitState(
    val unitItemList: List<UnitItem> = listOf(
        UnitItem(UnitType.METRIC, true),
        UnitItem(UnitType.IMPERIAL, false)
    )
)