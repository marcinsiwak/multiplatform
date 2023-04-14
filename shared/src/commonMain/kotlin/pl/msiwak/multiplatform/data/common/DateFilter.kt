package pl.msiwak.multiplatform.data.common

import dev.icerock.moko.resources.StringResource

data class DateFilter(
    val titleRes: StringResource,
    val isSelected: Boolean = false
)