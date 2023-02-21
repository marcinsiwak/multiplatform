package pl.msiwak.multiplatform.utils

import java.time.format.DateTimeFormatter
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime

actual class DateFormatter {

    actual fun formatDate(
        date: LocalDateTime,
        format: String
    ): String {
        val formatter = DateTimeFormatter.ofPattern(format)
        val javaDateTime = date.toJavaLocalDateTime()
        return javaDateTime.format(formatter)
    }
}
