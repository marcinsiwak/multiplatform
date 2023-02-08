package pl.msiwak.multiplatform.ui.summary

import pl.msiwak.multiplatform.data.entity.Summary

data class SummaryState(
    val summaries: List<Summary> = emptyList()
)