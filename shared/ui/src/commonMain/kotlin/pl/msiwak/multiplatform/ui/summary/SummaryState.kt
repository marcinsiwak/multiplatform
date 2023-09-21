package pl.msiwak.multiplatform.ui.summary

import pl.msiwak.multiplatform.commonObject.Category

data class SummaryState(
    val categories: List<Category> = emptyList()
)