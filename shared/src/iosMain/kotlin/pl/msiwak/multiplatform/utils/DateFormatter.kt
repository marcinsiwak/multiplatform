package pl.msiwak.multiplatform.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter

actual class DateFormatter {

    actual fun formatDate(
        date: LocalDateTime,
        format: String
    ): String {

        val dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = format
        return date.toNSDateComponents().date?.let { dateFormatter.stringFromDate(it) } ?: ""
    }

    actual fun formatString(
        date: String,
        format: String
    ): LocalDateTime {
        val dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = format
        val newDate = dateFormatter.dateFromString(date) ?: NSDate()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm"
        val finalDate = dateFormatter.stringFromDate(newDate)
        return LocalDateTime.parse(finalDate)
    }
}