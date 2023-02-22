package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.repository.SummaryRepository

class InsertSummariesUseCase(private val summaryRepository: SummaryRepository) {
    operator fun invoke(summaries: List<Summary>) {
        summaryRepository.insertSummaries(summaries)
    }
}