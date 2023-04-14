package pl.msiwak.multiplatform.data.common

data class UnitItem(
    val unitType: UnitType = UnitType.METRIC,
    val isSelected: Boolean = false
)