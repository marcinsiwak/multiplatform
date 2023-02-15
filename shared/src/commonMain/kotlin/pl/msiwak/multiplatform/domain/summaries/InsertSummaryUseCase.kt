package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.repository.SummaryRepository

class InsertSummaryUseCase(private val summaryRepository: SummaryRepository) {
    operator fun invoke(summary: Summary) {
        summaryRepository.insertSummary(summary)
    }
}