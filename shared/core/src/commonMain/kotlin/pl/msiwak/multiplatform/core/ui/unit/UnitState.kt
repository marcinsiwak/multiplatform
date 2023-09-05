package pl.msiwak.multiplatform.core.ui.unit

import pl.msiwak.multiplatform.commonObject.UnitItem
import pl.msiwak.multiplatform.commonObject.UnitType

data class UnitState(
    val unitItemList: List<UnitItem> = listOf(
        UnitItem(UnitType.METRIC, true),
        UnitItem(UnitType.IMPERIAL, false)
    )
)