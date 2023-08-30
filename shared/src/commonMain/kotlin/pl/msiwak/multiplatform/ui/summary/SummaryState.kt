package pl.msiwak.multiplatform.ui.summary

import pl.msiwak.multiplatform.data.common.Category

data class SummaryState(
    val categories: List<Category> = emptyList()
)