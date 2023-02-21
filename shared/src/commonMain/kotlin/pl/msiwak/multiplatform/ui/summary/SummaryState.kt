package pl.msiwak.multiplatform.ui.summary

import pl.msiwak.multiplatform.data.entity.SummaryEntity

data class SummaryState(
    val summaries: List<SummaryEntity> = emptyList()
)