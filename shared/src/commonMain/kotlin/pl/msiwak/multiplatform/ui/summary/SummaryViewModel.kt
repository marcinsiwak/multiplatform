package pl.msiwak.multiplatform.ui.summary

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pl.msiwak.multiplatform.ViewModel
import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.domain.summaries.GetSummariesUseCase
import pl.msiwak.multiplatform.domain.summaries.InsertSummariesUseCase

class SummaryViewModel(
    insertSummariesUseCase: InsertSummariesUseCase,
    getSummariesUseCase: GetSummariesUseCase
) : ViewModel() {

    private val _summaryState = MutableStateFlow(SummaryState())
    val summaryState: StateFlow<SummaryState> = _summaryState

    init {
        insertSummariesUseCase(listOf(Summary("sprint 500m", "50s"), Summary("sprint 200m", "40s")))
        val summaries = getSummariesUseCase()
        _summaryState.value = _summaryState.value.copy(summaries = summaries)
    }
}