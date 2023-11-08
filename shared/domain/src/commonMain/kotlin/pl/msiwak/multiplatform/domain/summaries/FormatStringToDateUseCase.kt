package pl.msiwak.multiplatform.domain.summaries

import kotlinx.datetime.LocalDateTime
import pl.msiwak.multiplatform.utils.DATE_FORMAT
import pl.msiwak.multiplatform.utils.DATE_TIME_FORMAT
import pl.msiwak.multiplatform.utils.DateFormatter

class FormatStringToDateUseCase(private val dateFormatter: DateFormatter) {
    operator fun invoke(date: String): LocalDateTime {
        return dateFormatter.formatString(date, DATE_TIME_FORMAT)
    }
}