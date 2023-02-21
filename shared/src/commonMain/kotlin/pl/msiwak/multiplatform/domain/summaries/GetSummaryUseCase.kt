package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.SummaryEntity
import pl.msiwak.multiplatform.repository.SummaryRepository

class GetSummaryUseCase(private val summaryRepository: SummaryRepository) {
    operator fun invoke(id: Long): SummaryEntity {
        return summaryRepository.getSummary(id)
    }
}