package pl.msiwak.multiplatform.domain.summaries

import pl.msiwak.multiplatform.data.entity.SummaryEntity
import pl.msiwak.multiplatform.repository.SummaryRepository

class GetSummariesUseCase(private val summaryRepository: SummaryRepository) {
    operator fun invoke(): List<SummaryEntity> {
        return summaryRepository.getSummaries()
    }
}
