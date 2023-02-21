package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.SummaryEntity
import pl.msiwak.multiplatform.repository.SummaryRepository

class InsertSummariesUseCase(private val summaryRepository: SummaryRepository) {
    operator fun invoke(summaries: List<SummaryEntity>) {
        summaryRepository.insertSummaries(summaries)
    }
}