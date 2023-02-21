package pl.msiwak.multiplatform.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toNSDateComponents
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
}