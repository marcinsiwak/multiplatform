package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.repository.SummaryRepository

class RemoveSummaryUseCase(private val summaryRepository: SummaryRepository) {
    operator fun invoke(summary: Summary) {
        summaryRepository.removeSummary(summary.id)
    }
}