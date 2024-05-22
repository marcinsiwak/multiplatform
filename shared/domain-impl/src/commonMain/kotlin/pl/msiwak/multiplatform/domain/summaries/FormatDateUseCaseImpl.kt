package pl.msiwak.multiplatform.domain.summaries

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import pl.msiwak.multiplatform.utils.DateFormatter

class FormatDateUseCaseImpl(private val dateFormatter: DateFormatter) : FormatDateUseCase {
    override operator fun invoke(date: Long): String {
        val localDateTime = Instant.fromEpochMilliseconds(date).toLocalDateTime(TimeZone.currentSystemDefault())
        return dateFormatter.formatDate(localDateTime)
    }
}
