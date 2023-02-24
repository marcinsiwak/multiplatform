package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.SummaryData
import pl.msiwak.multiplatform.repository.SummaryRepository

class GetSummariesUseCase(private val summaryRepository: SummaryRepository) {
    suspend operator fun invoke(): List<SummaryData> {
        return summaryRepository.getSummaries()
    }
}
