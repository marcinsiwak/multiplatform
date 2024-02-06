package pl.msiwak.multiplatform.domain.summaries

import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.utils.DateFormatter

class FormatDateUseCaseImpl(private val dateFormatter: DateFormatter) : FormatDateUseCase {
    override operator fun invoke(date: LocalDateTime): String {
        return dateFormatter.formatDate(date)
    }
}
