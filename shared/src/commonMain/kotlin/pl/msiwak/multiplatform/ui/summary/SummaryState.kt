package pl.msiwak.multiplatform.ui.summary

import pl.msiwak.multiplatform.data.entity.CategoryData

data class SummaryState(
    val categories: List<CategoryData> = emptyList()
)