package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.Summary
import pl.msiwak.multiplatform.repository.SummaryRepository

class GetSummariesUseCase(private val summaryRepository: SummaryRepository) {
    operator fun invoke(): List<Summary> {
        return summaryRepository.getSummaries()
    }
}
