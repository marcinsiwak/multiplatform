package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.SummaryData
import pl.msiwak.multiplatform.repository.SummaryRepository

class GetSummaryUseCase(private val summaryRepository: SummaryRepository) {
    suspend operator fun invoke(id: Long): SummaryData {
        return summaryRepository.getSummary(id)
    }
}