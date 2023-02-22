package pl.msiwak.multiplatform.domain.summaries

import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.utils.DateFormatter

class FormatDateUseCase(private val dateFormatter: DateFormatter) {
    operator fun invoke(date: LocalDateTime): String {
        return dateFormatter.formatDate(date)
    }
}