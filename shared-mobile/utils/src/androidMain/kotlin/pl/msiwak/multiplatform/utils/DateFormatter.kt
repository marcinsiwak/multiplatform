package pl.msiwak.multiplatform.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

actual class DateFormatter {

    actual fun formatDate(
        date: LocalDateTime,
        format: String
    ): String {
        val formatter = DateTimeFormatter.ofPattern(format)
        val javaDateTime = date.toJavaLocalDateTime()
        return javaDateTime.format(formatter)
    }

    actual fun formatString(
        date: String,
        format: String
    ): LocalDateTime {
        val format1 = SimpleDateFormat(format)
        val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
        val newDate = format1.parse(date)
        return format2.format(newDate).toLocalDateTime()
    }
}
