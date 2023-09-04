package pl.msiwak.multiplatform.core.ui.summary

import pl.msiwak.multiplatform.core.data.common.Category

data class SummaryState(
    val categories: List<Category> = emptyList()
)