package pl.msiwak.multiplatform.utils

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime

expect class DateFormatter() {
    fun formatDate(
        date: LocalDateTime,
        format: String = DATE_FORMAT
    ): String

    fun formatString(
        date: String,
        format: String
    ): LocalDateTime
}