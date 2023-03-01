package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.SummaryData
import pl.msiwak.multiplatform.repository.SummaryRepository

class InsertSummaryUseCase(private val summaryRepository: SummaryRepository) {
    suspend operator fun invoke(summaryData: SummaryData): Long {
        return summaryRepository.insertSummary(summaryData)
    }
}