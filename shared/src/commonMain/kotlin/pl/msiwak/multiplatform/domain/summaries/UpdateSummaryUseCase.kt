package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.SummaryData
import pl.msiwak.multiplatform.repository.SummaryRepository

class UpdateSummaryUseCase(private val summaryRepository: SummaryRepository) {
    suspend operator fun invoke(summaryData: SummaryData) {
        summaryRepository.updateSummary(summaryData)
    }
}