package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.repository.SummaryRepository

class GetSummaryUseCase(private val summaryRepository: SummaryRepository) {
    operator fun invoke(id: Long): Summary {
        return summaryRepository.getSummary(id)
    }
}