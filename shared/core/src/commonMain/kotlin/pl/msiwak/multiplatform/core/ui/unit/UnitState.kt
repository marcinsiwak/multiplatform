package pl.msiwak.multiplatform.core.ui.unit

import pl.msiwak.multiplatform.core.data.common.UnitItem
import pl.msiwak.multiplatform.core.data.common.UnitType

data class UnitState(
    val unitItemList: List<UnitItem> = listOf(
        UnitItem(UnitType.METRIC, true),
        UnitItem(UnitType.IMPERIAL, false)
    )
)