package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.SummaryEntity
import pl.msiwak.multiplatform.repository.SummaryRepository

class InsertSummaryUseCase(private val summaryRepository: SummaryRepository) {
    operator fun invoke(summaryEntity: SummaryEntity) {
        summaryRepository.insertSummary(summaryEntity)
    }
}