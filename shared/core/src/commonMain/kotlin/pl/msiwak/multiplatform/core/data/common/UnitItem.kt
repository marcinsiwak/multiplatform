package pl.msiwak.multiplatform.core.data.common

data class UnitItem(
    val unitType: UnitType = UnitType.METRIC,
    val isSelected: Boolean = false
)