package pl.msiwak.multiplatform.data.common

data class DateFilter(
    val type: DateFilterType,
    val isSelected: Boolean = false
)