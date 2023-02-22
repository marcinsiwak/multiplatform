package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.repository.SummaryRepository

class UpdateSummaryUseCase(private val summaryRepository: SummaryRepository) {
    suspend operator fun invoke(summary: Summary) {
        summaryRepository.updateSummary(summary)
    }
}