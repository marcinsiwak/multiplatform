package pl.msiwak.multiplatform.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter

actual class DateFormatter {

    actual fun formatDate(
        date: LocalDateTime,
        format: String
    ): String {
        val dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = format
        val nsDate = date.toInstant(TimeZone.currentSystemDefault()).toNSDate()
        return dateFormatter.stringFromDate(nsDate)
    }

    actual fun formatString(
        date: String,
        format: String
    ): LocalDateTime {
        return LocalDateTime.parse(date)
    }
}
