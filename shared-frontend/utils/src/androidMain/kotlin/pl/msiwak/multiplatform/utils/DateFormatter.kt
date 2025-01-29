package pl.msiwak.multiplatform.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
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
        return java.time.LocalDateTime.parse(date).toKotlinLocalDateTime()
    }
}
