package pl.msiwak.multiplatform.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.internal.JSJoda.DateTimeFormatter

actual class DateFormatter actual constructor() {
    actual fun formatDate(date: LocalDateTime, format: String): String {
        val formatter = DateTimeFormatter.ofPattern(format)
        return kotlinx.datetime.internal.JSJoda.LocalDateTime.of(
            date.year,
            date.monthNumber,
            date.dayOfMonth,
            date.hour,
            date.minute,
            date.second,
            date.nanosecond
        ).format(formatter)
    }

    actual fun formatString(date: String, format: String): LocalDateTime {
        val jsJodaDateTime = kotlinx.datetime.internal.JSJoda.LocalDateTime.parse(date)

        return LocalDateTime(
            jsJodaDateTime.year(),
            jsJodaDateTime.monthValue(),
            jsJodaDateTime.dayOfMonth(),
            jsJodaDateTime.hour(),
            jsJodaDateTime.minute(),
            jsJodaDateTime.second(),
            jsJodaDateTime.nano().toInt()
        )
    }
}
