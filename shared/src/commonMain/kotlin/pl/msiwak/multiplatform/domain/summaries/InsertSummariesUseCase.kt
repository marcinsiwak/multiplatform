package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.SummaryData
import pl.msiwak.multiplatform.repository.SummaryRepository

class InsertSummariesUseCase(private val summaryRepository: SummaryRepository) {
    suspend operator fun invoke(summaries: List<SummaryData>) {
        summaryRepository.insertSummaries(summaries)
    }
}