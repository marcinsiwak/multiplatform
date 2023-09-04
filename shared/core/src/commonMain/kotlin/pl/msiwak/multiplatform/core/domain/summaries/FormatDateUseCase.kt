package pl.msiwak.multiplatform.core.domain.summaries

import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.core.utils.DateFormatter

class FormatDateUseCase(private val dateFormatter: DateFormatter) {
    operator fun invoke(date: LocalDateTime): String {
        return dateFormatter.formatDate(date)
    }
}